package webUi.enums;

public enum Teams {
    NAPOLI ("Наполи", 47),
    MILAN ("Милан", 38),
    JUVENTUS ("Ювентус", 37),
    INTER ("Интер", 37),
    LAZIO ("Лацио", 34),
    ATALANTA ("Аталанта", 31),
    ROMA ("Рома", 31),
    UDINESE ("Удинезе", 25),
    FIORENTINA ("Фиорентина", 23),
    TORINO ("Торино", 23),
    MONZA ("Монца", 21),
    LECCE ("Лечче", 20),
    EMPOLI ("Эмполи", 19),
    BOLOGNA ("Болонья", 19),
    SALERNITANA ("Салернитана", 18),
    SASSUOLO ("Сассуоло", 16),
    SPICE ("Специя", 15),
    VERONA ("Верона", 9),
    SAMPDORIA ("Сампдория", 9),
    CREMONESE ("Кремонезе", 7);

    String name;
    int point;

    Teams (String name, int point) {this.name = name; this.point = point;}
    public String getName() {return name;}
    public int getPoint() {return point;}
}