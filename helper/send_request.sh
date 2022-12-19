curl --location --request POST 'http://localhost:8080/person' \
--header 'Content-Type: application/xml' \
--data-raw '<?xml version="1.0" encoding="UTF-8" ?>
<person>
    <personId>9536ffde-c794-48f4-8902-463ecf1fb418</personId>
    <version>0</version>
    <firstname>Hans</firstname>
    <lastname>Beisel</lastname>
    <department>IT Architecture</department>
</person>'