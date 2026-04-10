import com.affectionsboot.auth.infra.SmsCodeCache;
import com.affectionsboot.auth.utils.PasswordService;
import com.jinHan.gold.admin.AdminApplication;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类名: TokenHandlerTest
 * 描述:
 * 作者: xuzeyu
 * 创建时间: 2025/12/24
 */
@SpringBootTest(classes = AdminApplication.class)
public class TokenHandlerTest {
    @Resource
    private SmsCodeCache smsCodeCache;

    @Resource
    private PasswordService passwordService;
    @Test
    void testGenerateToken() {
        smsCodeCache.setCache("17691339623","123456");
//        System.out.println(passwordService.passwordEncrypt("123456"));
    }
}
