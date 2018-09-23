package ru.otus.guzev.lesson01obfuscation;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
public class StartObfuscation {

    public static void main(String[] args) {
        try {
            HttpResponse<String> jsonResponse = Unirest.get("http://bilet.pp.ru/calculator_rus/tochnoe_moskovskoe_vremia.php")
                    .header("Content-Type", "charset=windows-1251").asString();

            String responce = jsonResponse.getBody();
            int index = responce.lastIndexOf("Точное время в Москве:");
            System.out.println(responce.substring(index, index+22));
            System.out.println(responce.substring(index+30, index+38));
        } catch (UnirestException e){
            e.printStackTrace();
        }

    }
}
