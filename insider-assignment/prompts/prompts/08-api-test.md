# Goal

User API testlerini bağımsız ve sürdürülebilir hale getirmek.

# Context provided

Rest Assured + TestNG kullanıyorum. Mevcut UserCrudTest yapısında testler birbirine bağımlı olduğu için daha stabil ve parallel-safe bir yapı istiyorum.

# Prompt

add User API tests to make all tests independent. - Each test must create its own random/unique user (no shared data) - POST, GET, PUT, DELETE, and negative GET must all be self-contained - Use UUID or timestamp for unique usernames/ids - Each test must include its own setup + assertions - Keep Rest Assured best practices (given/when/then, proper validations) - Ensure tests can run in parallel safely - Maintain clean and readable code structure Refactor UserCrudTest accordingly.

# Output evaluation

AI bağımsız test yapısına doğru şekilde refactor yaptı. Testler arasındaki dependency kaldırıldı ve her test kendi datasını üretmeye başladı.

# Iteration notes

İlk versiyonda testler hala shared state içeriyordu. Sonrasında her testin kendi user’ını oluşturması sağlandı ve parallel execution uyumluluğu artırıldı. Finalde testler tamamen bağımsız hale getirildi.
