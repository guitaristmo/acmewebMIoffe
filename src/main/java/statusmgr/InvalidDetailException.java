package statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="some reason")
public class InvalidDetailException extends RuntimeException
{
    public InvalidDetailException(String message)
    {
        super(message);
    }
}