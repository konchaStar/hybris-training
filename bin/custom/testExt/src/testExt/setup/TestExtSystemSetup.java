/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package testExt.setup;

import static testExt.constants.TestExtConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import testExt.constants.TestExtConstants;
import testExt.service.TestExtService;


@SystemSetup(extension = TestExtConstants.EXTENSIONNAME)
public class TestExtSystemSetup
{
	private final TestExtService testExtService;

	public TestExtSystemSetup(final TestExtService testExtService)
	{
		this.testExtService = testExtService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		testExtService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TestExtSystemSetup.class.getResourceAsStream("/testExt/sap-hybris-platform.png");
	}
}
