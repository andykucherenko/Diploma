# Приложение «Мобильный хоспис»
## Процедура запуска автотестов:

Pre-condition:
- Устаноленный IntelliJ IDDEA с эмулятором на API 29. 
- На эмуляторе отключена анимация.

Steps:
1. Клонировать проект - https://github.com/andykucherenko/Diploma.git
2. Открыть его в Android Studio
3. Запустить настроенный эмулятор
4. Открыть терминал в корневой папке проекта и запустить команду "./gradlew connectedAndroidTest --info"
для запуска определнного теста ввести команду, например: "./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=ru.iteco.fmhandroid.ui.tests.AboutPageTests"
флаг --info нужен для более подробной информации о выполнении тестов

Для работы только через терминал:
1. Клонировать проект - https://github.com/andykucherenko/Diploma.git
2. В терминале перейти в папку с эмулятором и запустить его, пример:
  'cd ~/Library/Android/sdk/emulator
   ./emulator -avd <имя_AVD>'
3. В терминале перейти в корневую папку с проектом и выполнить команду "./gradlew connectedAndroidTest --info"

## Процедура просмотра отчета allure:
Для просмотра отчета allure:
1. Распаковать архив allure-results.rar
2. В терминале выполнить команду allure serve
