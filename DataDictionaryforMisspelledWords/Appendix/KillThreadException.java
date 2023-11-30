
/**
 * An exception used to signal that we want to kill the application thread.
 */

public final class KillThreadException extends RuntimeException
{
	public KillThreadException(String s)
	{
		super(s);
	}
}
