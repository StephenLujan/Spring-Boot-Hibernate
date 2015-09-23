package com.catalystdevworks.slujan.service;

/**
 * Exception thrown when a service is expected to but cannot find a matching
 * {@link User} entity.
 *
 */
public class UserNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = 5852749450963891456L;

	/**
	 * 
	 */
	public UserNotFoundException()
	{
	}

	/**
	 * @param message
	 */
	public UserNotFoundException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserNotFoundException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
