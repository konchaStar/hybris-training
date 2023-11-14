/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package testExt.service;

public interface TestExtService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
