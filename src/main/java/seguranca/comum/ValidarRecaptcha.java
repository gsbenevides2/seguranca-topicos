package seguranca.comum;

public interface ValidarRecaptcha {
    public boolean executar(String responseToken);
}
