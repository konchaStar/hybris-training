package concerttours.cjperform;

import concerttours.model.NewsModel;
import concerttours.model.SendNewsCronJobModel;
import concerttours.service.NewsService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.mail.MailUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class SendNewsPerformable extends AbstractJobPerformable<SendNewsCronJobModel> {
    private static final String EMAIL_CONTENT_ITEM = "%s\n%s\n\n";
    private static final String EMAIL_SUBJECT = "Todays news";
    private static final String PROPERTY_RECIPIENT = "news_mailing_address";

    @Autowired
    private NewsService newsService;
    @Autowired
    private ConfigurationService configurationService;

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public PerformResult perform(SendNewsCronJobModel sendNewsCronJobModel) {
        final List<NewsModel> newsItems = newsService.getNewsOfDay(new Date());

        if (CollectionUtils.isEmpty(newsItems)) {
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }

        try {
            sendEmail(createEmailContent(newsItems));
        } catch (EmailException e) {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private void sendEmail(String message) throws EmailException {
        Configuration configuration = configurationService.getConfiguration();
        String recipient = configuration.getString(PROPERTY_RECIPIENT);

        Email email = createEmail(recipient, message);

        email.send();
    }

    private String createEmailContent(List<NewsModel> newsItems) {
        StringBuilder message = new StringBuilder();

        newsItems.stream()
                .forEach(item -> {
                    message.append(String.format(EMAIL_CONTENT_ITEM, item.getHeadline(), item.getContent()));
                });

        return message.toString();
    }

    private Email createEmail(String recipient, String message) throws EmailException {
        Email email = MailUtils.getPreConfiguredEmail();

        email.addTo(recipient);
        email.setSubject(EMAIL_SUBJECT);
        email.setMsg(message);
        email.setTLS(true);

        return email;
    }
}
