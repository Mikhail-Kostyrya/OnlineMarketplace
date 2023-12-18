# Online Marketplace

## Описание проекта

Online Marketplace - это веб-приложение, представляющее собой онлайн-рынок, где пользователи могут просматривать, выбирать и покупать товары. Проект создан с использованием Java и включает в себя модели данных, хранящиеся в памяти, а также репозитории для взаимодействия с этими данными. Приложение поддерживает следующие сущности: клиенты, адреса, заказы, продукты, склады и т.д.
![](sql_diargamm.png)


## Основные функции

1. **Управление клиентами**: Добавление, просмотр и редактирование данных о клиентах, включая их адреса.

2. **Управление товарами**: Добавление, просмотр и редактирование информации о продуктах, их ценах и описаниях.

3. **Оформление заказов**: Создание и просмотр заказов, включая выбор товаров, указание адреса доставки и статус заказа.

4. **Управление складами**: Добавление, просмотр и редактирование данных о складах, где хранятся продукты.
   Проект "Online Marketplace" включает следующие классы данных:

## Модели
1. **Address (Адрес):**
    - Поля: улица (`street`), город (`city`), почтовый индекс (`postalCode`), идентификатор клиента (`clientId`).
    - Связь: множество адресов принадлежит одному клиенту.

2. **Client (Клиент):**
    - Поля: имя (`firstName`), фамилия (`lastName`), электронная почта (`email`).
    - Связь: каждый клиент имеет множество адресов и заказов.

3. **Order (Заказ):**
    - Поля: статус заказа (`status`), идентификатор клиента (`clientId`), идентификатор адреса доставки (`shippingAddressId`).
    - Связь: каждый заказ принадлежит одному клиенту и связан с одним адресом.

4. **OrderProduct (Товар в заказе):**
    - Поля: идентификатор заказа (`orderId`), идентификатор продукта (`productId`), количество товара (`quantity`).
    - Связь: каждый товар в заказе связан с одним заказом и одним продуктом.

5. **Product (Продукт):**
    - Поля: название (`name`), цена (`price`), описание (`description`), идентификатор склада (`warehouseId`).
    - Связь: каждый продукт связан с одним складом.

6. **Warehouse (Склад):**
    - Поля: название (`name`), адрес (`address`).
    - Связь: множество продуктов хранится на одном складе.

Каждый класс данных (`Address`, `Client`, `Order`, `OrderProduct`, `Product`, `Warehouse`) является подклассом абстрактного класса `Model`, который содержит уникальный идентификатор (`id`).

```java
public abstract class Model {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
```

Классы используют аннотации Lombok для удобного создания геттеров, сеттеров и конструкторов.

## Репозитории
Проект имеет архитектуру, в которой использованы репозитории для работы с данными, а также классы для представления моделей и их хранения в памяти. Вот краткое описание каждого класса:

1. **`Repository<T extends Model>`:**
   - Интерфейс, предоставляющий базовые методы для работы с объектами типа `T` (create, read, update, delete).
   - Поддерживает операции чтения всех объектов, чтения объекта по идентификатору и обновления объекта по идентификатору.

2. **`AddressRepository` (расширяет `Repository<Address>`):**
   - Интерфейс для работы с адресами, включая дополнительный метод `getByClientId` для получения адресов по идентификатору клиента.

3. **`OrderRepo` (расширяет `Repository<Order>`):**
   - Интерфейс для работы с заказами, включая дополнительный метод `getByClientId` для получения заказов по идентификатору клиента.

4. **`ProductRepository` (расширяет `Repository<Product>`):**
   - Интерфейс для работы с продуктами, включая дополнительный метод `getByOrderId` для получения продуктов по идентификатору заказа.

5. **`RepoLib`:**
   - Интерфейс, предоставляющий методы для получения репозиториев различных моделей (адреса, клиента, заказа и т. д.).

6. **Классы репозиториев в памяти (`AddressMemoryRepo`, `ClientMemoryRepo`, `OrderMemoryRepo`, `OrderProductMemoryRepo`, `ProductMemoryRepo`, `WarehouseMemoryRepo`):**
   - Реализации репозиториев в памяти, основанные на абстрактном классе `RepositoryMemory`.
   - Включают дополнительные методы проверки внешних ключей и получения объектов по определенным критериям.

7. **`MemoryRepoLib`:**
   - Реализация интерфейса `RepoLib`, предоставляющая экземпляры репозиториев в памяти для работы с адресами, клиентами, заказами и т. д.

Проект структурирован и использует подходы, облегчающие работу с данными в памяти. Репозитории в памяти предоставляют базовые методы для работы с данными, а интерфейс `RepoLib` абстрагирует доступ к различным репозиториям моделей.

## Генератор данных и Сериализация/Десериализация

### Генератор данных (Generator)

Генератор (`Generator`) представляет собой инструмент для создания фиктивных данных и наполнения хранилища. Он создает клиентов, адреса, склады, продукты, заказы и связи между ними для имитации работы онлайн-рынка.

#### Пример использования:

```java
RepoLib repoLib = MemoryRepoLib.getINSTANCE();
Generator.Generate(repoLib);
```

Генератор создает случайных клиентов, адреса, склады и продукты, а также связывает их между собой.

### Сериализация и Десериализация (Serializer и Deserializer)

`Serializer` и `Deserializer` предназначены для сохранения и загрузки данных из файла. Они используют библиотеку Jackson для работы с форматом JSON.

#### Пример использования сериализации:

```java
Serializer.serialize(new File("dump.json"), repoLib);
```

Сериализатор сохраняет текущее состояние хранилища в JSON-файл.

#### Пример использования десериализации:

```java
Deserializer.deserializer(new File("dump.json"), repoLib);
```

Десериализатор восстанавливает данные из JSON-файла, создавая объекты и восстанавливая связи между ними.

### Класс DbWrapper

`DbWrapper` служит для обертывания списков объектов разных классов и предоставляет единый интерфейс для сериализации и десериализации.

## Запуск приложения

Главный класс `Main` позволяет выбрать между генерацией новых данных и загрузкой существующих. В зависимости от выбора, происходит либо вызов генератора, либо десериализация из файла.

```java
public class Main {
    public static void main(String[] args) {
        // ...

        if (Objects.equals(s, "w")) {
            Generator.Generate(repoLib);
            Serializer.serialize(new File("dump.json"), repoLib);
        } else {
            Deserializer.deserializer(new File("dump.json"), repoLib);
            printInfo();
            // ...

        }
    }
    // ...
}
```

Выбор "w" означает запись данных, а "r" - чтение.

## Дополнительные функции

### Печать информации о клиентах и заказах

Метод `printInfo` выводит информацию о клиентах и связанных с ними заказах. Это полезный метод для проверки корректности данных после загрузки.

```java
public static void printInfo() {
    // ...
}
```

## Использованные подходы/инструменты
### Инструменты:

#### 1. **Lombok:**
- **Описание:** Lombok – это библиотека для Java, предназначенная для автоматизации рутинных задач в коде, таких как генерация геттеров, сеттеров, конструкторов и других методов.
- **Пример:**
  ```java
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public class Address extends Model {
      protected String street;
      protected String city;
      protected String postalCode;
      protected long clientId;
  }
  ```
- **Рассказ:**
   - Аннотации Lombok упрощают написание классов, делая код более читаемым и уменьшая объем шаблонного кода.
   - Пример выше демонстрирует использование аннотаций для генерации геттеров, конструкторов и сеттеров.

#### 2. **Jackson (ObjectMapper):**
- **Описание:** Jackson – это библиотека для обработки форматов данных JSON в Java. `ObjectMapper` из Jackson используется для преобразования объектов Java в JSON и наоборот.
- **Пример:**
  ```java
  public class Serializer {
      public static void serialize(File file, RepoLib repoLib){
          ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
          DbWrapper dbWrapper = new DbWrapper(
                  repoLib.getAddressRepo().read(),
                  repoLib.getAClientRepo().read(),
                  repoLib.getOrderRepo().read(),
                  repoLib.getOrderProductRepo().read(),
                  repoLib.getProductRepo().read(),
                  repoLib.getWarehouseRepo().read()
          );
          try {
              objectWriter.writeValue(file, dbWrapper);
          } catch (JsonProcessingException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new IllegalArgumentException("wrong file");
          }
      }
  }
  ```
- **Рассказ:**
   - `ObjectMapper` упрощает сериализацию объектов в JSON и десериализацию JSON в объекты.
   - Пример демонстрирует использование `ObjectMapper` для записи объектов из репозиториев в файл JSON.

### Подходы:

#### 1. **Инкапсуляция:**
- **Описание:** Инкапсуляция – это принцип объектно-ориентированного программирования, заключающийся в ограничении доступа к внутренним компонентам объекта и предоставлении доступа только через внешний интерфейс.
- **Пример:**
  ```java
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public class Address extends Model {
      protected String street;
      protected String city;
      protected String postalCode;
      protected long clientId;
  }
  ```
- **Рассказ:**
   - Аннотация `@Getter` из Lombok генерирует геттеры для полей класса, предоставляя доступ только для чтения.

#### 2. **Полиморфизм:**
- **Описание:** Полиморфизм позволяет объектам использовать методы своих подтипов. В Java это может быть достигнуто через переопределение методов в подклассах.
- **Пример:**
  ```java
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public class DbWrapper {
      List<Address> addresses;
      List<Client> clients;
      List<Order> orders;
      List<OrderProduct> orderProducts;
      List<Product> products;
      List<Warehouse> warehouses;
  }
  ```
- **Рассказ:**
   - Класс `DbWrapper` использует полиморфизм для хранения коллекций различных моделей (адресов, клиентов и т. д.) в одном объекте.

#### 3. **Наследование:**
- **Описание:** Наследование позволяет классу наследовать свойства и методы от другого класса. В Java это достигается с помощью ключевого слова `extends`.
- **Пример:**
  ```java
  public class AddressRepositoryMemory extends RepositoryMemory<Address> implements AddressRepository {
      // реализация методов AddressRepository
  }
  ```
- **Рассказ:**
   - Класс `AddressRepositoryMemory` наследует функциональность от абстрактного класса `RepositoryMemory` и реализует интерфейс `AddressRepository`.

#### 4. **Singleton:**
- **Описание:** Синглтон гарантирует, что у класса есть только один экземпляр, и предоставляет глобальную точку доступа к этому экземпляру.
- **Пример:**
  ```java
  public class AddressMemoryRepo extends RepositoryMemory<Address> implements AddressRepository {
      private AddressMemoryRepo() { }

      private static AddressMemoryRepo INSTANCE;

      public static AddressMemoryRepo getINSTANCE(){
          if(INSTANCE == null) {
              INSTANCE = new AddressMemoryRepo();
          }
          return INSTANCE;
      }
      // ...
  }
  ```
- **Рассказ:**
   - Класс `AddressMemoryRepo` реализует шаблон синглтона, чтобы гарантировать существование только одного экземпляра репозитория в памяти.

