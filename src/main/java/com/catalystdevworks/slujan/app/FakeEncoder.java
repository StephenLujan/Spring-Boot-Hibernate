package com.catalystdevworks.slujan.app;

import org.springframework.security.crypto.password.PasswordEncoder;

public class FakeEncoder implements PasswordEncoder
{
	@Override
	public String encode(CharSequence rawPassword)
	{
		return (String) rawPassword;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		return this.encode(rawPassword).equals(encodedPassword);
	}
}
