# Goal

QA job list’ten seçilen bir iş için “Apply” butonuna tıklayıp Lever Application form sayfasına doğru şekilde yönlendirme olup olmadığını doğrulamak.

# Context provided

Selenium + TestNG + POM yapısı kullanıyorum. QAJobsTest içinde Istanbul lokasyonlu QA job zaten bulunuyor. Şimdi seçilen job üzerinden Apply flow’unu test edeceğim.

# Prompt

Click “Apply” button and check that this action redirects us to Lever Application form page. @insider-assignment/src/test/java/com/insiderOne/ui/tests/ApplyRedirectTest.java içine yaz. @insider-assignment/src/test/java/com/insiderOne/ui/tests/QAJobsTest.java deki testte istanbul locasyonlu iligli iş ünvanı kontrolü yaptık. şimdide o işin apply tıklayacağız. tıkladıktan sonra açılan sayfada .posting-headline h2 bu elementte "Software Quality Assurance Engineer (Remote)" bu değer yazıyorsa doğru sayfaya yönlendirildi demekttir. bir de buna ek olarak a.template-btn-submit bu element varmı bunu kontrol et. bu iki kontrol okeyse doğru sayfayı açılmış demekttir.

# Output evaluation

AI doğru şekilde end-to-end apply flow oluşturdu ve Lever application sayfasına yönlendirme kontrolünü ekledi. Element doğrulamaları yerindeydi. Ancak bazı adımlar (apply click ve form validation) başlangıçta tek test içinde dağınık yazılmıştı, sonradan ApplyRedirectTest içinde daha temiz bir flow haline getirildi.

# Iteration notes

İlk versiyonda job selection ve apply action aynı test içinde karmaşıktı. Sonrasında QAJobsTest ile ApplyRedirectTest ayrıştırıldı. Ayrıca form açılma doğrulaması için #resume-upload-input kontrolü eklenerek test daha sağlam hale getirildi.
