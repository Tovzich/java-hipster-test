# java-hipster-test
Товмач Г.П.
Рыбаков В.А.

В эндпоинте api/countries нашли баг автокодогенерилки - поля regionId (id) запроса и id ответа схлопнулось в одно поле, из-за чего спецификация оказалась с ошибкой.
Мы вызывали это апи через веб интерфейс и обнаружили ошибку. После чего вручную исправили класс модели и получили успешный результат. 