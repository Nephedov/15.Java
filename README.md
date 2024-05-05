# Домашнее задание к занятию «Интерфейсы для организации малой связности. Обобщённое программирование (Generics)»

## Решения
### Задание 1
* <a href="https://github.com/Nephedov/15.Java/blob/main/src/test/java/TicketSearchTest.java">TicketSearchTest.java</a> - класс с автотестами.
* <a href="https://github.com/Nephedov/15.Java/blob/main/src/main/java/TicketRepository.java">TicketRepository.java</a> - класс-репозиторий для "билетов".
* <a href="https://github.com/Nephedov/15.Java/blob/main/src/main/java/TicketManager.java">TicketManager.java</a> - класс-менеджер поиска "билетов".
* <a href="https://github.com/Nephedov/15.Java/blob/main/src/main/java/Ticket.java">Ticket.java</a> - класс описывающий "билет".

Ветка <a href="https://github.com/Nephedov/15.Java/tree/main">main</a> с проектом.
### Задание 2
* <a href="https://github.com/Nephedov/15.Java/blob/fast/src/test/java/TicketSearchTest.java">TicketSearchTest.java</a> - класс с автотестами.
* <a href="https://github.com/Nephedov/15.Java/blob/fast/src/main/java/TicketByTimeAscComparator.java">TicketByTimeAscComparator.java</a> - класс Comparator сравнения билетов по цене.
* <a href="https://github.com/Nephedov/15.Java/blob/fast/src/main/java/TicketManager.java">TicketManager.java</a> - с методом реализующим интерфейс Comparator<Ticket>.

Ветка <a href="https://github.com/Nephedov/15.Java/tree/fast">fast</a> с проектом.
## Что было сделано
### Задание 1
* Создан Maven проект и настроен <a href="https://github.com/Nephedov/15.Java/blob/main/pom.xml">pom.xml</a> с плагинами и зависимостями:
  * JunitJupier.
  * Surefire.
  * Jacoco в режиме генерации отчетов и обрушения сборки по покрытию 100% по бранчам методов.
  * Настроен <a href="https://github.com/Nephedov/15.Java/blob/main/.github/workflows/maven.yml">maven.yml</a> для Github CI.
* Реализован класс для описывающий информацию о билете - <a href="https://github.com/Nephedov/15.Java/blob/main/src/main/java/Ticket.java">Ticket.java</a>, используемый интерфейс Comparable.
* Реализован класс-репозиторий для хранения информации о билетах: добавить, удалить, получить набор билетов -
  <a href="https://github.com/Nephedov/15.Java/blob/main/src/main/java/TicketRepository.java">TicketRepository.java</a>.
* Реализован класс-менеджер поиска - <a href="https://github.com/Nephedov/15.Java/blob/main/src/main/java/TicketManager.java">TicketManager.java</a>.
* Релизован класс с автотестами - <a href="https://github.com/Nephedov/15.Java/blob/main/src/test/java/TicketSearchTest.java">TicketSearchTest.java</a>.
### Задание 2
* Создана ветка <a href="https://github.com/Nephedov/15.Java/tree/fast">fast</a> из ветки <a href="https://github.com/Nephedov/15.Java/tree/main">main</a> в которой:
  * Реализован класс
    <a href="https://github.com/Nephedov/15.Java/blob/fast/src/main/java/TicketByTimeAscComparator.java">TicketByTimeAscComparator.java</a> - используемый в тестовом классе
    <a href="https://github.com/Nephedov/15.Java/blob/fast/src/test/java/TicketSearchTest.java">TicketSearchTest.java</a>.
  * В класс <a href="https://github.com/Nephedov/15.Java/blob/fast/src/main/java/TicketManager.java">TicketManager.java</a>, добавлен метод findAll принимающий объект любого класса, реализующего интерфейс Comparator<Ticket>.
  * Добавлены проверяющие сортировку - <a href="https://github.com/Nephedov/15.Java/blob/fast/src/test/java/TicketSearchTest.java">TicketSearchTest.java</a>.
# Описание Задания 1. Поиск билетов (обязательное к выполнению)

Вы работаете в сервисе по продаже авиабилетов онлайн.

![image](https://user-images.githubusercontent.com/53707586/154491051-0bc17b53-cf07-4502-80c0-6379e1a89b92.png)

Что вам нужно сделать:
1. Спроектируйте класс для информации о билете.
1. Реализуйте репозиторий для хранения информации о билетах: добавить, удалить, получить набор билетов.
1. Реализуйте менеджера поиска по аэропорту вылета и аэропорту прилёта, даты не учитывайте.

#### Информация о билете

Класс информации о билете — это data-класс, который должен содержать:
1. ID.
1. Стоимость, для упрощения будем считать стоимость единой для всех продавцов.
1. Аэропорт вылета, вы можете использовать [IATA-коды](https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%B4_%D0%B0%D1%8D%D1%80%D0%BE%D0%BF%D0%BE%D1%80%D1%82%D0%B0_%D0%98%D0%90%D0%A2%D0%90).
1. Аэропорт прилёта, вы можете использовать [IATA-коды](https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%B4_%D0%B0%D1%8D%D1%80%D0%BE%D0%BF%D0%BE%D1%80%D1%82%D0%B0_%D0%98%D0%90%D0%A2%D0%90).
1. Время в пути в минутах.

Других данных не нужно.

Этот класс должен реализовывать интерфейс `Comparable<...>` так, чтобы по умолчанию сортировка происходила по цене, самый дешёвый — самый первый.

### Менеджер

В менеджере методов `findAll` должен претерпеть некоторые изменения — он должен принимать два параметра:
* `from` — аэропорта вылета,
* `to` —  аэропорт прилёта.

Значит, в результате поиска возвращается массив только с теми билетами, что соответствуют условиям поиска. Методы поиска вы уже делать умеете.

Кроме того, результаты должны быть отсортированы по цене от меньшей к большей.

### Автотесты

Напишите автотесты на поиск, удостоверившись, что он удовлетворяет условиям задачи. Количество тестов и тестируемые сценарии мы оставляем на ваше усмотрение.

# Описание Задания 2*. Самый быстрый (необязательная задача)

Иногда необходима сортировка не только по цене, но и, например, по времени — люди хотят найти самый быстрый перелёт.

Естественно, ваш сервис идёт навстречу пожеланиям клиентов и решает добавить такую возможность.

Но как мы это сделаем, ведь наши билеты уже сортируются по цене.

### `Comparator`

Помимо интерфейса `Comparable`, который определяет порядок сортировки объектов данного класса по умолчанию, у нас есть интерфейс `Comparator`, который позволяет создавать объекты, определяющие порядок сортировки других объектов.

Как это выглядит, мы покажем на примере сортировки по цене по возрастанию — аналог, который реализован вами в первой задаче:

```java
public class TicketByPriceAscComparator implements Comparator<Ticket> {
  public int compare(Ticket o1, Ticket o2) {
    return o1.getPrice() - o2.getPrice();
  }
}
```

Обратите внимание: это отдельный специальный класс, который умеет сравнивать два объекта типа «Билет».

Логика интерпретации возвращаемого из метода `compare` значения аналогична логике `compareTo`.

В отдельной ветке `fast` того же репозитория улучшите сервис, создав метод `findAll(String from, String to, Comparator<Ticket> comparator)`.

Этот метод делает всё то же самое, что и обычный `findAll` из первой задачи, но сортирует не методом `Arrays.sort(result)`, а `Arrays.sort(result, comparator)`.

Таким образом, вы сможете передавать в этот метод объект любого класса, реализующего интерфейс `Comparator<Ticket>`.
