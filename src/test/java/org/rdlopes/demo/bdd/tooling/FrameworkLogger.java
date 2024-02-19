package org.rdlopes.demo.bdd.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FrameworkLogger {

    //ZmE4ZTUwNTgtYWViZC0zNjcxLThkZDMtYWVkZGI0NTQxOGMxLjViMjdlZTYxLTViZjktNDlkZS1hY2FlLWZlNmIwOGIyNTkwMQ==
    private static BufferedWriter writer = null;

    // Inicia o Logger, criando o arquivo se necessário
    public static void StartLogging() throws IOException {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String logFileName = "log_" + dateTime + ".txt";
        File logFile = new File("logs/" + logFileName);

        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();
        }

        writer = new BufferedWriter(new FileWriter(logFile, true));
    }

    // Escreve uma mensagem no arquivo de log
    public static void LogAttach(String message, String elementId) throws IOException {
        if (elementId != null) {
            message = MessageFormat.format(message, elementId);
        }

        if (writer != null) {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } else {
            System.out.println(message);
        }
    }

    // Fecha o arquivo de log
    public static void stopLogging() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    public static void LogPreviousMethod(int level) throws IOException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 2) {
            StackTraceElement callingMethod = stackTrace[level];
            String methodName = callingMethod.getMethodName();

            LogAttach(MessageFormat.format("Method caller: {0}", methodName), null);
            LogAttach(MessageFormat.format("Método chamador: {0}", methodName), null);

        } else {
            LogAttach("Impossible to determinate the method caller", null);
            LogAttach("Não é possível determinar o método chamador.", null);
        }
    }
    public static void LogElement_Found(Behaviors.GetElementBy by, String webElement) throws IOException {
        LogAttach("\n", "\n");
        LogPreviousMethod(4);

        LogAttach(MessageFormat.format("\t Element \"{1}\" successfully found by ", by, webElement), null);
        LogAttach(MessageFormat.format("\t Elemento \"{1}\" encontrado com sucesso por ", by, webElement), null);

    }

    public static void LogElement_NotFound(Behaviors.GetElementBy by, String webElement) throws IOException {
        LogAttach("\n", "\n");
        LogPreviousMethod(4);

        LogAttach(MessageFormat.format("\t Element \"{1}\" was not found by: ", by, webElement), null);

        LogAttach("\t Tip: Consider working with the development team to include an ID in this web element.", null);
        LogAttach("\t Dica: Considere trabalhar com a equipe de desenvolvimento para incluir um ID neste elemento web.", null);
    }

    public static  void LogElement_Click(Behaviors.GetElementBy by, String webElement) throws IOException {
        LogAttach("\n", "\n");
        LogPreviousMethod(4);

        LogAttach(MessageFormat.format("\t Element \"{1}\" was successfully clicked by {0}", by, webElement), null);
        LogAttach(MessageFormat.format("\t Elemento \"{1}\" foi clicado com sucesso por {0}", by, webElement), null);
    }

    public static void LogElement_Input(String text, String webElement) throws IOException {
        LogAttach("\n", "\n");
        LogPreviousMethod(4);

        LogAttach(MessageFormat.format("\t The value \"{0}\" was inserted on element \"{1}\"", text, webElement), null);
        LogAttach(MessageFormat.format("\t Foi inserido o valor \"{0}\" no campo \"{1}\"", text, webElement), null);
    }

    public static void LogStringAssertions(String expected_message, String actual_message) throws IOException {
        LogAttach("\n", "\n");
        LogPreviousMethod(4);

        LogAttach(MessageFormat.format("\t Expected message: \"{0}\" ", expected_message), null);
        LogAttach(MessageFormat.format("\t Received message: \"{0}\" ", actual_message), null);
        LogAttach(MessageFormat.format("\t Mensagem esperada: \"{0}\" ", expected_message), null);
        LogAttach(MessageFormat.format("\t Mensagem recebida: \"{0}\" ", actual_message), null);
    }


}
