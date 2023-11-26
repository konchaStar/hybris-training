package concerttours.controller;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ProductController implements Controller {
    private static final String CATALOG_ID = "hwcatalog";
    private static final String CATALOG_NAME = "Online";
    private static final String CODE_PARAM = "code";
    private static final String PRODUCT_ATTRIBUTE = "product";
    private CatalogService catalogService;
    private ProductService productService;

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        catalogService.setSessionCatalogVersion(CATALOG_ID, CATALOG_NAME);
        String code = request.getParameter(CODE_PARAM);
        ProductModel product = null;
        if (!StringUtils.isBlank(code)) {
            product = productService.getProduct(code);
        }
        Map<String, Object> model = new HashMap<>();
        model.put(PRODUCT_ATTRIBUTE, product);
        return new ModelAndView("product.jsp", model);
    }
}
