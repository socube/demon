package menagerie;

import java.lang.annotation.*;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface ClusterSafe {
}
