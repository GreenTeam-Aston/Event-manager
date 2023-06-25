Event-manager 

<b>О проекте</b>
<p>В данном репозитории содержится (ну если честно, то пока нет!) реализация мнолита для работы нашего приложения</p>
Стек проекта:

 - Java 17
 - Maven
 - Spring Boot
 - Spring Security
 - Swagger
 - Flyway
 - СУБД PostgreSQL(на начальном этапе H2 database)

<b>Git flow</b>
<p>В проекте используется следующий git flow:</p>

 - ветка develop является основной, push на ветку  develop запрещен
 - задачи в виде нововведений в проект необходимо реализовывать в ветке feature/EVENT-{фамилия}-{краткое-описание}, которая создается из ветки develop
 - задачи в виде исправлений багов необходимо реализовывать в ветке bugfix/EVENT-{фамилия}-{краткое-описание}, которая создается из ветки develop
 - далее создается Merge request (MR) в ветку develop
 - назначаем Reviewer - Кирилла/Петра и Assignee - себя
 - в случае возникновения конфликтов cлияния необходимо их решить
