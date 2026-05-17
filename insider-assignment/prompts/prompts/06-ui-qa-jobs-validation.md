# Goal

QA open positions sayfasına giderek listelenen işlerde Position, Department ve Location alanlarının doğru şekilde “Quality Assurance” ve “Istanbul, Turkey” içerdiğini doğrulamak.

# Context provided

Selenium + TestNG + POM yapısı kullanıyorum. CareersNavigationTest içinde QA team sayfasına geçiş zaten yapıldı. Şimdi open positions sayfasında job list validation yapacağım.

# Prompt

Check that all jobs’ Position contains “Quality Assurance”, Department contains
“Quality Assurance”, Location contains “Istanbul, Turkey”
bu testi @insider-assignment/src/test/java/com/insiderOne/ui/tests/QAJobsTest.java de yaz.
@insider-assignment/src/test/java/com/insiderOne/ui/tests/CareersNavigationTest.java de tüm listenen işlerde quality assurance bulduk. a[href*="team=Quality%20Assurance"] elemente tıklayarak açık pozisyonlara gideceğiz. https://jobs.lever.co/insiderone?team=Quality%20Assurance  bu sayfanın açıldığını doğrula. ardından listelenen işler arasında quality assurance olanlarda istanbul locasyonlusu var mı kontrol et.

# Output evaluation

AI doğru şekilde job filtering ve validation mantığını oluşturdu. List üzerinden iteration yaklaşımı doğruydu.

# Iteration notes


