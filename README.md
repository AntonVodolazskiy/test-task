# Storefront E2E Tests

UI-автоматизація веб-магазину на Java, Selenium WebDriver та Cucumber з BDD-підходом.

## BDD-структура

- Gherkin-сценарії описані у [storefront-shopping.feature](storefront-e2e-tests/src/test/resources/features/storefront-shopping.feature).
- Кроки `Given`, `When`, `Then`, `And` реалізовані у [ShoppingFlowSteps.java](storefront-e2e-tests/src/test/java/e2e/steps/ShoppingFlowSteps.java).
- Cucumber runner знаходиться у [StorefrontE2ETest.java](storefront-e2e-tests/src/test/java/e2e/StorefrontE2ETest.java).
- Ініціалізацію та закриття браузера для кожного сценарію виконує [BrowserHooks.java](storefront-e2e-tests/src/test/java/e2e/hooks/BrowserHooks.java).
- Взаємодію зі сторінкою ізольовано у Page Object: [StorefrontPage.java](storefront-e2e-tests/src/test/java/e2e/pages/StorefrontPage.java).

## Запуск

```zsh
cd storefront-e2e-tests
mvn clean test
```

Для headless-режиму:

```zsh
mvn clean test -Dheadless=true
```
