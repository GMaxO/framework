package database;

import api.dto.admin_dto.AstrologerDTO;
import asserts.CustomAssert;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static settings.SettingStorage.*;

public class DataBaseSteps {
    @Step("Устанавливаем в БД 'срок подписки истёк' по айди: {id}")
    public static void updateContractsById(int id) {
        Connection connection = connectToDb();
        DataBaseProvider.execSQLReq(connection,
                "update contracts set expiry_date = now(), " +
                        "subscriptions = null, first_payment_id = null,  last_payment_id = null, period = null, " +
                        "subscription_id = null where user_id =" + id);
        DataBaseProvider.disconnectFromDb(connection);
    }

    @Step("Устанавливаем все нули в таблицу орбов")
    public static void writeZeroToAllCells(int id) {
        Connection connection = connectToDb();
        String data = "'{\"title\":\"chronos.app.astroStore.myProfile\",\"overSign\":70,\"overSign0\":50,\"housesSystem\":{\"horar\":2,\"common\":0,\"northern\":3},\"idCustomization\":2,\"isRounded\":true,\"northernLatitude\":61.75,\"useNatalPlace\":true,\"startingPoint\":\"asc\",\"rotationDir\":\"ccw\",\"widgets\":{\"dispositors\":{\"planets\":[{\"id\":\"Chiron\",\"label\":\"astro.chiron\",\"isActive\":false},{\"id\":\"Lilith\",\"label\":\"astro.lilith\",\"isActive\":false},{\"id\":\"Nodes\",\"label\":\"astro.nodes\",\"isActive\":false},{\"id\":\"Selena\",\"label\":\"astro.selena\",\"isActive\":false}]}},\"fixedStars\":{\"natal\":{\"list\":[\"Algol\",\"Aldebaran\",\"Antares\",\"Betelgeuse\",\"Regulus\",\"Sirius\"],\"showWithObjects\":true},\"synastry\":{\"list\":[\"Algol\",\"Aldebaran\",\"Antares\",\"Betelgeuse\",\"Regulus\",\"Sirius\"],\"showWithObjects\":true},\"horar\":{\"list\":[\"Alpheratz\",\"Almaak\",\"Dubhe\",\"Castor\",\"Pollux\",\"Sirius\",\"Zuben Elgenubi\",\"Zuben Eschamali\",\"Sadalsuud\",\"Skat\",\"Capella\",\"Arcturus\",\"Seginus\",\"Princeps\",\"Diadem\",\"Algorab\",\"Rasalgethi\",\"Alphard\",\"Spica\",\"Vindemiatrix\",\"Rastaban\",\"Yed Prior\",\"Sinistra\",\"Unukalhai\",\"Schedar\",\"Canopus\",\"Foramen\",\"Diphda\",\"Baten Kaitos\",\"Nashira\",\"Deneb Algedi\",\"Oculus\",\"Bos\",\"Deneb Adige\",\"Vega\",\"Regulus\",\"Denebola\",\"Zosma\",\"Al Jabhah\",\"Procyon\",\"Hamal\",\"Sheratan\",\"Betelgeuse\",\"Rigel\",\"Bellatrix\",\"Alnilam\",\"Altair\",\"Deneb el Okab Australis\",\"Markab\",\"Algol\",\"Acubens\",\"Asellus Borealis\",\"Asellus Australis\",\"Alphecca\",\"Antares\",\"Acumen\",\"Ascella\",\"Manubrium\",\"Terebellium\",\"Facies\",\"Aldebaran\",\"El Nath\",\"Ain\",\"Al Hecka\",\"Alcyone\",\"Toliman\",\"Agena\",\"Labrum\",\"Achernar\",\"Phact\",\"Fomalhaut\",\"Aculeus\",\"Praesepe Cluster\"],\"showWithObjects\":true}},\"fixedGmt\":{\"1974-09-29T04:07:00.000Z\":{\"gmt\":4,\"lat\":13.75398,\"lon\":100.50144,\"range\":[\"1200-01-01T00:00:00.000Z\",\"2023-04-11T03:59:59.000Z\"]},\"1974-09-29T04:08:00.000Z\":{\"gmt\":4,\"lat\":28.53834,\"lon\":-81.37924,\"range\":[\"1955-04-24T03:00:00.000Z\",\"2022-11-06T01:59:59.000Z\"]},\"1988-06-23T15:14:43.000Z\":{\"gmt\":0,\"lat\":43.70011,\"lon\":-79.4163,\"range\":[\"1918-04-14T10:00:00.000Z\",\"2022-11-06T08:59:59.000Z\"]}},\"maps\":{\"natal\":{\"orbises\":{\"0\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\",\"30\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"36\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"40\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"45\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\",\"60\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\",\"72\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"80\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"90\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\",\"100\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"108\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"120\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\",\"135\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\",\"144\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"150\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\",\"180\":\"0 0 0 0 0 0 0 0 0 0 !0 !0 !0 !0\"},\"orbiseCorrector\":0},\"solars\":{\"orbises\":{\"0\":\"2 2 2 2 2 2 2 2 2 2 2 2 !0 2\",\"30\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"36\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"40\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"45\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"60\":\"2 2 2 2 2 2 2 2 2 2 2 2 !0 2\",\"72\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"80\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"90\":\"2 2 2 2 2 2 2 2 2 2 2 2 !0 2\",\"100\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"108\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"120\":\"2 2 2 2 2 2 2 2 2 2 2 2 !0 2\",\"135\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"144\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"150\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"180\":\"2 2 2 2 2 2 2 2 2 2 2 2 !0 2\"},\"orbiseCorrector\":0},\"synastry\":{\"orbises\":{\"0\":\"10 9 5 5 5 7 5 5 5 5 5 5 !0 !4\",\"30\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"36\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"40\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"45\":\"1.5 1 1 1 1 1 1 1 1 1 1 1 !0 !0\",\"60\":\"10 9 5 5 5 7 5 5 5 5 5 5 !0 !4\",\"72\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"80\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"90\":\"10 9 5 5 5 7 5 5 5 5 5 5 !0 !4\",\"100\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"108\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"120\":\"10 9 5 5 5 7 5 5 5 5 5 5 !0 !4\",\"135\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 !0\",\"144\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"150\":\"3 2 1 1 1 1 1 1 1 1 1 1 !0 !0\",\"180\":\"10 9 5 5 5 7 5 5 5 5 5 5 !0 !4\"},\"orbiseCorrector\":-60},\"transits\":{\"orbises\":{\"0\":\"2 2 2 2 2 2 2 2 2 2 2 2 2 2\",\"30\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"36\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"40\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"45\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"60\":\"2 2 2 2 2 2 2 2 2 2 2 2 2 2\",\"72\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"80\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"90\":\"2 2 2 2 2 2 2 2 2 2 2 2 2 2\",\"100\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"108\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"120\":\"2 2 2 2 2 2 2 2 2 2 2 2 2 2\",\"135\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"144\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"150\":\"!0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0 !0\",\"180\":\"2 2 2 2 2 2 2 2 2 2 2 2 2 2\"},\"orbiseCorrector\":0},\"directions\":{\"orbises\":{\"0\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"30\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"36\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"40\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"45\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"60\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"72\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"80\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"90\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"100\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"108\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"120\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"135\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"144\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"150\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"180\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\"},\"orbiseCorrector\":0},\"progressions\":{\"orbises\":{\"0\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"30\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"36\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"40\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"45\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"60\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"72\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"80\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"90\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"100\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"108\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"120\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\",\"135\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"144\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"150\":\"1 1 1 1 1 1 1 1 1 1 1 1 !0 1\",\"180\":\"2 1.5 1 1 1 1 1 1 1 1 1 1 !0 1\"},\"orbiseCorrector\":0}}}'";
        DataBaseProvider.execSQLReq(connection, "update public.astro_settings set data = " + data + " where user_id = " + id);
        DataBaseProvider.disconnectFromDb(connection);
    }

    @Step("Удаляем блоки в кабинете Рассвета по айди: {id}")
    public static void updateDawnBlocksById(int id) {
        Connection connection = connectToDb();
        DataBaseProvider.execSQLReq(connection,
                "update formations set data='{}' where user_id = " + id);
        DataBaseProvider.disconnectFromDb(connection);
    }

    @Step("Удаляем консультацию по айди: {id}")
    public static void updateConsultationById(int id) {
        Connection connection = connectToDb();
        DataBaseProvider.execSQLReq(connection,
                "update work_sessions set removed = now() where id = " + id);
        DataBaseProvider.disconnectFromDb(connection);
    }

    @Step("Получаем список астрологов из БД")
    public static List<AstrologerDTO> getAstrologersListFromDb() throws SQLException {
        ResultSet rs = getAstrologerRS();
        List<AstrologerDTO> astrologersFromDbList = convertResultSetToList(rs);
        CustomAssert.assertTrue(astrologersFromDbList.size() > 0,
                "получили список астрологов: " + astrologersFromDbList.size() + " шт.");
        return astrologersFromDbList;
    }

    private static List<AstrologerDTO> convertResultSetToList(ResultSet rs) throws SQLException {
        List<AstrologerDTO> astrologersList = new ArrayList<>();
        while (rs.next()) {
            String data = rs.getString("data");
            JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
            JsonArray formatsObject = jsonObject.getAsJsonArray("formats");
            List<String> listFormats = new ArrayList<>();
            formatsObject.forEach(s -> listFormats.add(s.getAsString()));

            AstrologerDTO astrologer = new AstrologerDTO();
            astrologer.setUserId(rs.getInt("userId"));
            astrologer.setActive(rs.getBoolean("isActive"));
            astrologer.setHasSchedule(rs.getBoolean("hasSchedule"));
            astrologer.setFullName(rs.getString("fullName"));
            astrologer.setFormats(listFormats);
            astrologersList.add(astrologer);
        }
        return astrologersList;
    }

    @Step("Получаем из базы данных список астрологов вместе с услугами")
    public static Map<Integer, List<String>> getAstrologerCompetenciesFromDb() throws SQLException {
        ResultSet rs = getAstrologerRS();
        Map<Integer, List<String>> astrologerWithCompetencies = new HashMap<>();

        while (rs.next()) {
            Integer id = rs.getInt("userId");
            String data = rs.getString("data");
            JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();

            JsonObject competenciesObject = jsonObject.getAsJsonObject("cv");
            JsonArray arr = competenciesObject.getAsJsonArray("competencies");
            List<String> competencies = new ArrayList<>();
            arr.forEach(s -> competencies.add(s.getAsString()));

            astrologerWithCompetencies.put(id, competencies);
        }
        CustomAssert.assertTrue(astrologerWithCompetencies.size() > 0,
                "получили список астрологов с услугами: " + astrologerWithCompetencies.size() + " шт.");
        return astrologerWithCompetencies;
    }

    private static Connection connectToDb() {
        return DataBaseProvider.connectToDb(
                getDbUrlProperty("db.url"),
                getStringProperty("db.username"),
                getStringProperty("db.password"));
    }

    private static ResultSet getAstrologerRS() {
        Connection connection = connectToDb();
        ResultSet rs = DataBaseProvider.executeSQLRequest(connection, "SELECT\n" +
                "  COUNT(*) OVER() AS total,\n" +
                "  P.\"userId\",\n" +
                "  (P.data::json->>'isLocked')::bool AS \"isLocked\",\n" +
                "  (P.data::json->>'isActive')::bool AS \"isActive\",\n" +
                "  (P.data::json->>'interviewStatus')::integer AS \"interviewStatus\",\n" +
                "  P.data,\n" +
                "  (\n" +
                "    (((P.schedule::json->>'days')::json->>0)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>1)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>2)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>3)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>4)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>5)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>6)::json->>'isActive')::boolean\n" +
                "  ) AS \"hasSchedule\",\n" +
                "  email,\n" +
                "  phone_number,\n" +
                "  CONCAT(first_name, ' ', last_name) as \"fullName\",\n" +
                "  avatar_url,\n" +
                "  birth,\n" +
                "  ((P.data::json->>'cv')::json->>'dateTime')::timestamptz AS \"createdAt\",\n" +
                "  ((P.data::json->>'exam')::json->>'status')::integer AS \"examStatus\"\n" +
                "FROM work_profiles AS P\n" +
                "  JOIN users AS U ON U.id = P.\"userId\"\n" +
                "  JOIN accounts AS A ON A.user_id = P.\"userId\"\n" +
                "WHERE (P.data->>'cv')::json IS NOT NULL AND\n" +
                "  (P.data->>'isActive')::bool = true\n" +
                "  AND P.data ->> 'formats' like '%\"indi\"%' AND (\n" +
                "    (((P.schedule::json->>'days')::json->>0)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>1)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>2)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>3)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>4)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>5)::json->>'isActive')::boolean OR\n" +
                "    (((P.schedule::json->>'days')::json->>6)::json->>'isActive')::boolean\n" +
                "  ) = true\n" +
                "ORDER BY P.\"userId\" ASC");
        DataBaseProvider.disconnectFromDb(connection);
        return rs;
    }
}
