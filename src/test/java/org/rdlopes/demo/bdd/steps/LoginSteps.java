package org.rdlopes.demo.bdd.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.rdlopes.demo.bdd.pages.login_page.LoginPage;
import org.rdlopes.demo.bdd.tooling.FrameworkLogger;
import org.rdlopes.demo.bdd.tooling.WebDriverConfigurations;

import java.io.IOException;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();


    @BeforeAll
    public static void before_all() throws IOException {
        WebDriverConfigurations.ConfigureChromeWebDriver();
        FrameworkLogger.StartLogging();
    }

    @AfterAll
    public static void after_all() throws InterruptedException {
        WebDriverConfigurations.TerminateDriver();
    }

    @Dado("estar na página de login")
    public void estar_na_página_de_login() {
        loginPage.GoToMainPage();
    }

    @Quando("inserir credenciais válidas, clicar no botão entrar")
    public void inserir_credenciais_válidas_clicar_no_botão_entrar() throws IOException {
        loginPage.EnterEmail("automation@automation.com");
        loginPage.EnterPassword("12345");
        loginPage.ClickEnterButton();
    }

    @Quando("inserir login válido e senha inválida, clicar no botão entrar")
    public void inserir_login_válido_e_senha_inválida_clicar_no_botão_entrar() throws IOException {
        loginPage.EnterEmail("automation@automation.com");
        loginPage.EnterPassword("12346");
        loginPage.ClickEnterButton();
    }

    @Quando("inserir login inválido e senha válida, clicar no botão entrar")
    public void inserir_login_inválido_e_senha_válida_clicar_no_botão_entrar() throws IOException {
        loginPage.EnterEmail("automation@automation.co");
        loginPage.EnterPassword("12345");
        loginPage.ClickEnterButton();
    }

    @Quando("inserir login inválido e senha inválida, clicar no botão entrar")
    public void inserir_login_inválido_e_senha_inválida_clicar_no_botão_entrar() throws IOException {
        loginPage.EnterEmail("automation@automation.con");
        loginPage.EnterPassword("5699aS1!%¨^");
        loginPage.ClickEnterButton();
    }

    @Quando("inserir e-mail vazio e campo senha preenchido, clicar no botão entrar")
    public void inserir_e_mail_vazio_e_campo_senha_preenchido_clicar_no_botão_entrar() throws IOException {
        loginPage.EnterEmail("");
        loginPage.EnterPassword("12345");
        loginPage.ClickEnterButton();
    }

    @Quando("inserir e-mail e campo senha vazio, clicar no botão entrar")
    public void inserir_e_mail_e_campo_senha_vazio_clicar_no_botão_entrar() throws IOException {
        loginPage.EnterEmail("automation@automation.com");
        loginPage.EnterPassword("");
        loginPage.ClickEnterButton();
    }

    @Quando("campo e-mail vazio e campo senha vazio, clicar no botão entrar")
    public void campo_e_mail_vazio_e_campo_senha_vazio_clicar_no_botão_entrar() throws IOException {
        loginPage.EnterEmail("");
        loginPage.EnterPassword("");
        loginPage.ClickEnterButton();
    }

    @Entao("deve ser redirecionado para a tela inicial")
    public void deve_ser_redirecionado_para_a_tela_inicial() {
        //homePage.ValidateHomeScreen();
    }

    @Entao("não deve ser redirecionado para a tela inicial, deve mostrar a mensagem {string}")
    public void não_deve_ser_redirecionado_para_a_tela_inicial_deve_mostrar_a_mensagem(String expected_message) throws IOException {
        loginPage.ValidateMessageWrongCredentials(expected_message);
    }

    @Entao("não deve ser redirecionado para a tela inicial, deve mostrar a mensagem {string} e {string}")
    public void não_deve_ser_redirecionado_para_a_tela_inicial_deve_mostrar_a_mensagem_e(String expected_message_a, String expected_message_b) throws IOException {
        loginPage.ValidateMessagesWrongCredentials(expected_message_a, expected_message_b);
    }
}
