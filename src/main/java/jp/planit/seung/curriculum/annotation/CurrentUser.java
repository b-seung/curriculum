package jp.planit.seung.curriculum.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

// @Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.PARAMETER)
// @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
public @interface CurrentUser {
}
