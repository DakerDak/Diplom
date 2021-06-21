# Приложение «Мобильный хоспис»
## Правила именования в проекте (*для разрабочиков*):
* Правила именования пакетов и классов:

Имена пакетов всегда в нижнем регистре и без подчеркивания. Использование многословных имен обычно не рекомендуется, но если действительно нужно использовать несколько слов, можно просто объединить их вместе или использовать camelCase.

  Пример:
  * `repository
  `
  * ` viewModel
  `

Имена классов и объектов начинаются с заглавной буквы и используют PascalCase.

  Пример:
  * `open class DeclarationProcessor { /*...*/ }
  `
  * `object EmptyDeclarationProcessor : DeclarationProcessor() { /*...*/ }
  `

Имена функций, свойств и локальных переменных начинаются со строчной буквы и используют camelCase без подчеркивания.
  
  Пример:
  * `fun processDeclarations() { /*...*/ }`
  * `var declarationCount = 1`

Исключение: фабричные функции, используемые для создания экземпляров классов, могут иметь то же имя, что и абстрактный тип возвращаемого значения.

  Пример:
  * `interface Foo { /*...*/ }`

  * `class FooImpl : Foo { /*...*/ }`

  * `fun Foo(): Foo { return FooImpl() }`
  
Имена констант (свойства, помеченные как const, или свойства верхнего уровня или объекта val без пользовательской функции get, которые содержат глубоко неизменяемые данные) должны использовать имена, разделенные подчеркиванием в верхнем регистре (SCREAMING_SNAKE_CASE).
  
  Пример:
  * `const val MAX_COUNT = 8`
  * `val USER_NAME_FIELD = "UserName"`
  
Имена свойств верхнего уровня или объектов, которые содержат объекты с поведением или изменяемыми данными, должны использовать имена camelCase.
  
  Пример:
  * `val mutableCollection: MutableSet<String> = HashSet()`
  
Имена свойств, содержащих ссылки на одноэлементные объекты, могут использовать тот же стиль именования, что и объявления объектов.
  
  Пример:
  * `val PersonComparator: Comparator<Person> = /*...*/`
  
Для констант перечисления (Enum) использовать имена разделенные подчеркиванием в верхнем регистре (SCREAMING_SNAKE_CASE).

  Пример:
  * `Color {RED, GREEN}`

Если у класса есть два свойства, которые концептуально одинаковы, но одно является частью общедоступного API, а другое является деталью реализации, используется подчеркивание в качестве префикса для имени частного свойства.
  
  Пример:
  * ` class C {
    private val _elementList = mutableListOf<Element>()
    val elementList: List<Element>
         get() = _elementList
    }
    `

Для нейминга в xml *используется Snake Case*. Завершающим элементом в названии должен идти тип view (полностью).
  Больше ничего лишнего не добавляется:
  
  Пример:
    * `
          <ImageView
          android:id="@+id/line_divider_image_view"/>
    `
    * `
    <string name="date_not_set">Date not set</string>
    `
    * `
    <color name="light_grey">#F5F5F5</color>
    `

### Выбирайте хорошие имена

Имя класса обычно представляет собой существительное или именную фразу, объясняющую, что это за класс: List, PersonReader.

Имя метода обычно представляет собой глагол или глагольную фразу, говорящую о том, что делает метод: close, readPersons. Название также должно указывать на то, изменяет ли метод объект или возвращает новый. Например, sort сортирует коллекцию на месте, а sorted возвращает отсортированную копию коллекции.

Имена должны давать понять, какова цель сущности, поэтому лучше избегать использования в именах бессмысленных слов (Manager, Wrapper).

При использовании аббревиатуры как части имени объявления используйте заглавную букву, если она состоит из двух букв (IOStream); используйте только первую букву, если она длиннее (XmlFormatter, HttpInputStream).
