# Дипломный проект "QA Engineer"
## Запуск автотестов
<details>
<summary>Предусловия</summary>

- Установлена Android Studio
- Установлена последняя стабильная версия Android SDK
- Android эмулятор с API 29, с русским языком системы
- Установлен Allure

</details>



1. Открыть репозиторий в Android Studio.
2. Установить JAVA, используемую в проекте, на JAVA_HOME.
3. Собрать проект.
4. Запустить эмулятор Android или устройство с API 29.
5. Выполнить тесты командой "./gradlew connectedAndroidTest" в терминале Android Studio.
6. После завершения автотестов, сохранить папку "allure-results" по пути "Device Explorer/sdcard/googletests/test_outputfiles/allure-results".
7. В папке на уровень выше allure-results выполнить команду "allure serve", чтобы получить визуализацию результатов тестов в формате index.html.
