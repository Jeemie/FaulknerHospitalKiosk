package Map.Exceptions;

import Map.Map;

/**
 * Created by maryannoconnell on 4/13/16.
 */
public class DefaultFileDoesNotExistException extends Throwable {

    public DefaultFileDoesNotExistException() {

        System.err.println("Default file does not exist.");

    }
}
