# Goal

Selenium için POM tabanlı UI framework iskeletini oluşturmak.

# Context provided

UI testleri Selenium + TestNG ile yazılacak. Page Object Model kullanacağım ve frameworkü scalable hale getirmek istiyorum.

# Prompt

Implement Page Object Model structure.

Create ONLY skeleton classes (no heavy implementation yet), but with method signatures.

Pages:

BasePage
HomePage
CareersPage
OpenRolesPage
JobListPage

Tests:

HomePageTest
CareersNavigationTest
QAJobsTest
ApplyRedirectTest

Rules:

Locators must NOT be in test classes
Assertions must NOT be in page classes
Create reusable BaseTest
Create DriverFactory
Create WaitUtils

# Output evaluation

AI doğru bir POM iskeleti üretti ve class separation mantığı yerindeydi. Ancak bazı page class’larda gereksiz locator veya assertion önerileri vardı. Bunları temizleyip sadece method signature seviyesinde tuttum.

# Iteration notes

İlk çıktı fazla implementasyon içeriyordu. Sonrasında framework sadece skeleton olacak şekilde sadeleştirildi ve sorumluluk ayrımı (Page / Test / Utils) netleştirildi.
