![picture](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaM86sA_OFyywLcLkCNudDI-I-q7k__wbwhg&usqp=CAU)
### Курсовая работа. «Менеджер задач»

**Задача:** разработать сервер, отвечающий за менеджмент списка дел.
- Центральным для логики программы компонентом д.б объект класса TODOs, который должен содержать в себе набор задач, добавленных в систему. 
- Сервер должен принимать запросы на добавление или удаление задач из списка в формате json { "type": "ADD", "task": "Название задачи" },
где `type` - тип операции (`ADD` или `REMOVE`), а `task` - сама задача.  
- Также у этого объекта должна быть возможность получить все актуальные задачи разом через метод `getAllTasks` - метод возвращает все задачи через пробел **в отсортированном лексикографическом (словарном) порядке**.   
- в списке задач не должно быть больше чем 7 задач.
- написать тесты на основе JUnit 5, минимум 3 штуки.  

***Дополнительное задание:***
- Добавьте поддержку  операции "RESTORE". Она будет задаваться json-ом вида `{ "type": "RESTORE" }`.
В случае получения такой операции, сервер должен отменить действие последней не-RESTORE-операции.
