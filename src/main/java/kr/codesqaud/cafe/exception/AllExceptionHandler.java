package kr.codesqaud.cafe.exception;

import kr.codesqaud.cafe.exception.article.ArticleNotFoundException;
import kr.codesqaud.cafe.exception.user.DuplicateUserIdException;
import kr.codesqaud.cafe.exception.user.MismatchedPasswordException;
import kr.codesqaud.cafe.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(DuplicateUserIdException.class)
    public String handleDuplicateUserIdException(DuplicateUserIdException exception, Model model) {
        model.addAttribute("userSaveRequest", exception.getUserSaveRequest());
        model.addAttribute("duplicateUserIdMessage", exception.getMessage());
        return "user/sign-up";
    }

    @ExceptionHandler(MismatchedPasswordException.class)
    public String handleMismatchedPasswordException(MismatchedPasswordException exception, Model model) {
        model.addAttribute("mismatchedPasswordMessage", exception.getMessage());
        return "user/update";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException exception, Model model) {
        model.addAttribute("failMessage", exception.getMessage());
        return "exception/fail";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ArticleNotFoundException.class)
    public String handelArticleNotFoundException(ArticleNotFoundException exception, Model model) {
        model.addAttribute("failMessage", exception.getMessage());
        return "exception/fail";
    }
}