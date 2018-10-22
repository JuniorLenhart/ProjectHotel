package hotel.support;

import hotel.controller.LoggerController;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.*;

public class Localizacao {

    public static Endereco getEnderecoPorCep(String cep) {
        JsonObject jsonObject = getCepResponse(cep);
        Endereco endereco = null;
        JsonValue erro = jsonObject.get("erro");
        if (erro == null) {
            endereco = new Endereco()
                    .setCep(jsonObject.getString("cep"))
                    .setLogradouro(jsonObject.getString("logradouro"))
                    .setComplemento(jsonObject.getString("complemento"))
                    .setBairro(jsonObject.getString("bairro"))
                    .setLocalidade(jsonObject.getString("localidade"))
                    .setUf(jsonObject.getString("uf"))
                    .setUnidade(jsonObject.getString("unidade"))
                    .setIbge(jsonObject.getString("ibge"))
                    .setGia(jsonObject.getString("gia"));

        }
        return endereco;
    }

    public static Map<String, String> getMapPorCep(String cep) {
        JsonObject jsonObject = getCepResponse(cep);
        JsonValue erro = jsonObject.get("erro");
        Map<String, String> mapa = null;
        if (erro == null) {
            mapa = new HashMap<String, String>();
            for (Iterator<Map.Entry<String, JsonValue>> it = jsonObject.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, JsonValue> entry = it.next();
                mapa.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return mapa;
    }

    private static JsonObject getCepResponse(String cep) {
        JsonObject responseJO = null;
        try {
            if (!Validacao.validarCep(cep)) {
                throw new RuntimeException("Formato de CEP inválido");
            } else {
                DefaultHttpClient httpclient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("https://viacep.com.br/ws/" + cep + "/json");
                HttpResponse response = httpclient.execute(httpGet);

                HttpEntity entity = response.getEntity();

                responseJO = Json.createReader(entity.getContent()).readObject();
            }
        } catch (IOException | RuntimeException e) {
            LoggerController.log(Localizacao.class, e);
        }
        return responseJO;
    }
}
