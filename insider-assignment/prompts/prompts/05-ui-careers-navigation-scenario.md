# Goal

Insider careers sayfasında “We’re hiring” akışını test etmek ve QA job listesinde “Quality Assurance” pozisyonlarının göründüğünü doğrulamak.

# Context provided

Selenium + TestNG + POM yapısı kullanıyorum. Careers navigation için mevcut page class’larım var ve UI testlerini yeniden kullanılabilir şekilde yazıyorum.

# Prompt

On this page https://insiderone.com/careers/#open-roles, click "See all teams", go to."Quality Assurance" open positions, and check the presence of the jobs list. şimdi bu testi @insider-assignment/src/test/java/com/insiderOne/ui/tests/CareersNavigationTest.java  de yazacağız. adımları veriyorum. shouldOpenInsiderHomePageAndDisplayMainElements bu adımlar yapılır sonra a[data-text="We're hiring"] bu element bulunur ve tıklanır. bu element sayfanın altlarında. tıklandıktan sonra bu sayfanın açıldığını doğrula https://insiderone.com/careers/ . sonra a.inso-btn.see-more bu elementi bul ve tıkla. bu element aşağılarda biraz kaydırmak gerekecektir. sonra açılan iş listesinde Quality Assurance mevcut mu değil mi kontrol et. burada meslek alanını testten dinamik gönderebileyim. 

# Output evaluation

AI doğru bir E2E UI flow oluşturdu ve page object yaklaşımını korudu. Scroll ve dynamic element handling eklemesi faydalıydı.

# Iteration notes

İlk versiyonda test fazla “hardcoded” idi. Sonrasında department value dinamik hale getirildi ve UI elementlerinin page class’lara taşınması sağlandı.
