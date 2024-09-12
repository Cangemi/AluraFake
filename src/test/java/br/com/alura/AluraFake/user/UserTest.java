package br.com.alura.AluraFake.user;

import br.com.alura.AluraFake.util.EncryptUtil;
import org.junit.jupiter.api.Test;

import static br.com.alura.AluraFake.user.Role.STUDENT;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void password_should_be_encrypted_to_md5() {
        User user = new User("Caio Bugorin", "caio.bugorin@alura.com.br", STUDENT, "mudar123");
        assertThat(user.getPassword())
                .isEqualTo(EncryptUtil.toMD5("mudar123"));
    }

}