package com.azimut.arapcakelimeogreniyorum.data.local.seed

import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity

/**
 * Pre-populated initial seed data for Arabic learning application.
 */
object InitialDataSeed {

    val alphabetList = listOf(
        AlphabetEntity(
            orderIndex = 1,
            letterArabic = "أ",
            nameTurkish = "Elif",
            transliteration = "A / E",
            isolatedForm = "ا",
            initialForm = "اـ",
            medialForm = "ـاـ",
            finalForm = "ـا",
            description = "Boğazın alt kısmından çıkarılan, harekelerine göre A veya E sesi veren harftir.",
            exampleWordArabic = "أَسَد",
            exampleWordTurkish = "Aslan",
            audioResName = "audio_letter_elif"
        ),
        AlphabetEntity(
            orderIndex = 2,
            letterArabic = "ب",
            nameTurkish = "Be",
            transliteration = "B",
            isolatedForm = "ب",
            initialForm = "بـ",
            medialForm = "ـبـ",
            finalForm = "ـب",
            description = "İki dudağın iç kısımlarının birbirine değdirilmesiyle çıkarılan ince B sesidir.",
            exampleWordArabic = "بَيْت",
            exampleWordTurkish = "Ev",
            audioResName = "audio_letter_ba"
        ),
        AlphabetEntity(
            orderIndex = 3,
            letterArabic = "ت",
            nameTurkish = "Te",
            transliteration = "T",
            isolatedForm = "ت",
            initialForm = "تـ",
            medialForm = "ـتـ",
            finalForm = "ـت",
            description = "Dil ucunun üst ön dişlerin diplerine vurulmasıyla çıkarılan ince T sesidir.",
            exampleWordArabic = "تُفَّاح",
            exampleWordTurkish = "Elma",
            audioResName = "audio_letter_ta"
        ),
        AlphabetEntity(
            orderIndex = 4,
            letterArabic = "ث",
            nameTurkish = "Se (Peltik)",
            transliteration = "Th",
            isolatedForm = "ث",
            initialForm = "ثـ",
            medialForm = "ـثـ",
            finalForm = "ـث",
            description = "Dil ucunun üst ön dişlerin uçlarına hafifçe dokundurulmasıyla çıkarılan peltik ve ince S sesidir.",
            exampleWordArabic = "ثَعْلَب",
            exampleWordTurkish = "Tilki",
            audioResName = "audio_letter_tha"
        ),
        AlphabetEntity(
            orderIndex = 5,
            letterArabic = "ج",
            nameTurkish = "Cim",
            transliteration = "C",
            isolatedForm = "ج",
            initialForm = "جـ",
            medialForm = "ـجـ",
            finalForm = "ـج",
            description = "Dil ortasının üst damağa bastırılmasıyla çıkarılan yumuşak C sesidir.",
            exampleWordArabic = "جَمَل",
            exampleWordTurkish = "Deve",
            audioResName = "audio_letter_jim"
        ),
        AlphabetEntity(
            orderIndex = 6,
            letterArabic = "ح",
            nameTurkish = "Ha (Nefesli)",
            transliteration = "Ḥ",
            isolatedForm = "ح",
            initialForm = "حـ",
            medialForm = "ـحـ",
            finalForm = "ـح",
            description = "Boğazın ortasından, boğaz hafifçe sıkılarak çıkarılan keskin ve nefesli H sesidir.",
            exampleWordArabic = "حِصَان",
            exampleWordTurkish = "At",
            audioResName = "audio_letter_ha"
        ),
        AlphabetEntity(
            orderIndex = 7,
            letterArabic = "خ",
            nameTurkish = "Hı (Hırıltılı)",
            transliteration = "Kh",
            isolatedForm = "خ",
            initialForm = "خـ",
            medialForm = "ـخـ",
            finalForm = "ـخ",
            description = "Boğazın ağza en yakın kısmından çıkarılan hırıltılı ve kalın H sesidir.",
            exampleWordArabic = "خُبْز",
            exampleWordTurkish = "Ekmek",
            audioResName = "audio_letter_kha"
        ),
        AlphabetEntity(
            orderIndex = 8,
            letterArabic = "د",
            nameTurkish = "Dal",
            transliteration = "D",
            isolatedForm = "د",
            initialForm = "دـ",
            medialForm = "ـدـ",
            finalForm = "ـد",
            description = "Dil ucunun üst ön dişlerin diplerine temas ettirilmesiyle çıkarılan ince D sesidir.",
            exampleWordArabic = "دَفْتَر",
            exampleWordTurkish = "Defter",
            audioResName = "audio_letter_dal"
        ),
        AlphabetEntity(
            orderIndex = 9,
            letterArabic = "ذ",
            nameTurkish = "Zel (Peltik)",
            transliteration = "Dh",
            isolatedForm = "ذ",
            initialForm = "ذـ",
            medialForm = "ـذـ",
            finalForm = "ـذ",
            description = "Dil ucunun üst ön dişlerin uçlarına değdirilmesiyle çıkarılan peltik ve ince Z sesidir.",
            exampleWordArabic = "ذِئْب",
            exampleWordTurkish = "Kurt",
            audioResName = "audio_letter_dhal"
        ),
        AlphabetEntity(
            orderIndex = 10,
            letterArabic = "ر",
            nameTurkish = "Re",
            transliteration = "R",
            isolatedForm = "ر",
            initialForm = "رـ",
            medialForm = "ـرـ",
            finalForm = "ـر",
            description = "Dil ucunun arkasının üst damağa dokundurulmasıyla çıkarılan R sesidir.",
            exampleWordArabic = "رَجُل",
            exampleWordTurkish = "Adam",
            audioResName = "audio_letter_ra"
        ),
        AlphabetEntity(
            orderIndex = 11,
            letterArabic = "ز",
            nameTurkish = "Ze",
            transliteration = "Z",
            isolatedForm = "ز",
            initialForm = "زـ",
            medialForm = "ـزـ",
            finalForm = "ـز",
            description = "Dil ucunun alt ön dişlerin arkasına yaklaştırılmasıyla çıkarılan keskin ve ince Z sesidir.",
            exampleWordArabic = "زَهْرَة",
            exampleWordTurkish = "Çiçek",
            audioResName = "audio_letter_zay"
        ),
        AlphabetEntity(
            orderIndex = 12,
            letterArabic = "س",
            nameTurkish = "Sin",
            transliteration = "S",
            isolatedForm = "س",
            initialForm = "سـ",
            medialForm = "ـسـ",
            finalForm = "ـس",
            description = "Dil ucunun alt ön dişlerin arkasına yerleştirilmesiyle çıkarılan ince S sesidir.",
            exampleWordArabic = "سَمَك",
            exampleWordTurkish = "Balık",
            audioResName = "audio_letter_sin"
        ),
        AlphabetEntity(
            orderIndex = 13,
            letterArabic = "ش",
            nameTurkish = "Şin",
            transliteration = "Sh",
            isolatedForm = "ش",
            initialForm = "شـ",
            medialForm = "ـشـ",
            finalForm = "ـش",
            description = "Dil ortasının üst damağa yaklaştırılmasıyla çıkarılan Ş sesidir.",
            exampleWordArabic = "شَمْس",
            exampleWordTurkish = "Güneş",
            audioResName = "audio_letter_shin"
        ),
        AlphabetEntity(
            orderIndex = 14,
            letterArabic = "ص",
            nameTurkish = "Sad",
            transliteration = "Ṣ",
            isolatedForm = "ص",
            initialForm = "صـ",
            medialForm = "ـصـ",
            finalForm = "ـص",
            description = "Dilin arka kısmının damağa doğru yükseltilmesiyle çıkarılan dolgun ve kalın S sesidir.",
            exampleWordArabic = "صَنَدُوق",
            exampleWordTurkish = "Kutu",
            audioResName = "audio_letter_sad"
        ),
        AlphabetEntity(
            orderIndex = 15,
            letterArabic = "ض",
            nameTurkish = "Dad",
            transliteration = "Ḍ",
            isolatedForm = "ض",
            initialForm = "ضـ",
            medialForm = "ـضـ",
            finalForm = "ـض",
            description = "Dil yan tarafının üst azı dişlerine temasıyla çıkarılan kalın ve dolgun D/Z sesidir.",
            exampleWordArabic = "ضَوْء",
            exampleWordTurkish = "Işık",
            audioResName = "audio_letter_dad"
        ),
        AlphabetEntity(
            orderIndex = 16,
            letterArabic = "ط",
            nameTurkish = "Tı",
            transliteration = "Ṭ",
            isolatedForm = "ط",
            initialForm = "طـ",
            medialForm = "ـطـ",
            finalForm = "ـط",
            description = "Dil ucunun üst ön dişlerin diplerine kuvvetlice bastırılmasıyla çıkarılan kalın T sesidir.",
            exampleWordArabic = "طَالِب",
            exampleWordTurkish = "Öğrenci",
            audioResName = "audio_letter_ta2"
        ),
        AlphabetEntity(
            orderIndex = 17,
            letterArabic = "ظ",
            nameTurkish = "Zı (Peltik Kalın)",
            transliteration = "Ẓ",
            isolatedForm = "ظ",
            initialForm = "ظـ",
            medialForm = "ـظـ",
            finalForm = "ـظ",
            description = "Dil ucunun ön dişlerin arasına çıkarılmasıyla üretilen peltik ve kalın Z sesidir.",
            exampleWordArabic = "ظَرْف",
            exampleWordTurkish = "Zarf",
            audioResName = "audio_letter_za"
        ),
        AlphabetEntity(
            orderIndex = 18,
            letterArabic = "ع",
            nameTurkish = "Ayn",
            transliteration = "‘",
            isolatedForm = "ع",
            initialForm = "عـ",
            medialForm = "ـعـ",
            finalForm = "ـع",
            description = "Boğazın ortasının sıkılmasıyla çıkarılan özel geniz ve boğaz sesidir.",
            exampleWordArabic = "عَيْن",
            exampleWordTurkish = "Göz",
            audioResName = "audio_letter_ayn"
        ),
        AlphabetEntity(
            orderIndex = 19,
            letterArabic = "غ",
            nameTurkish = "Ğayn",
            transliteration = "Gh",
            isolatedForm = "غ",
            initialForm = "غـ",
            medialForm = "ـغـ",
            finalForm = "ـغ",
            description = "Boğazın ağza en yakın kısmından çıkarılan, Fransızca R sesine benzer hırıltılı Ğ sesidir.",
            exampleWordArabic = "غَزَال",
            exampleWordTurkish = "Ceylan",
            audioResName = "audio_letter_ghayn"
        ),
        AlphabetEntity(
            orderIndex = 20,
            letterArabic = "ف",
            nameTurkish = "Fe",
            transliteration = "F",
            isolatedForm = "ف",
            initialForm = "فـ",
            medialForm = "ـفـ",
            finalForm = "ـف",
            description = "Üst ön dişlerin alt dudağın iç kısmına değdirilmesiyle çıkarılan F sesidir.",
            exampleWordArabic = "فِيل",
            exampleWordTurkish = "Fil",
            audioResName = "audio_letter_fa"
        ),
        AlphabetEntity(
            orderIndex = 21,
            letterArabic = "ق",
            nameTurkish = "Kaf (Kalın)",
            transliteration = "Q",
            isolatedForm = "ق",
            initialForm = "قـ",
            medialForm = "ـقـ",
            finalForm = "ـق",
            description = "Dil kökünün yumuşak damağa vurulmasıyla çıkarılan derinden gelen kalın K sesidir.",
            exampleWordArabic = "قَلَم",
            exampleWordTurkish = "Kalem",
            audioResName = "audio_letter_qaf"
        ),
        AlphabetEntity(
            orderIndex = 22,
            letterArabic = "ك",
            nameTurkish = "Kef (İnce)",
            transliteration = "K",
            isolatedForm = "ك",
            initialForm = "كـ",
            medialForm = "ـكـ",
            finalForm = "ـك",
            description = "Dil kökünün biraz ön tarafının sert damağa değdirilmesiyle çıkarılan ince K sesidir.",
            exampleWordArabic = "كِتَاب",
            exampleWordTurkish = "Kitap",
            audioResName = "audio_letter_kaf"
        ),
        AlphabetEntity(
            orderIndex = 23,
            letterArabic = "ل",
            nameTurkish = "Lam",
            transliteration = "L",
            isolatedForm = "ل",
            initialForm = "لـ",
            medialForm = "ـلـ",
            finalForm = "ـل",
            description = "Dil ucunun üst damağa temasıyla çıkarılan yumuşak L sesidir.",
            exampleWordArabic = "لَيْمُون",
            exampleWordTurkish = "Limon",
            audioResName = "audio_letter_lam"
        ),
        AlphabetEntity(
            orderIndex = 24,
            letterArabic = "م",
            nameTurkish = "Mim",
            transliteration = "M",
            isolatedForm = "م",
            initialForm = "مـ",
            medialForm = "ـمـ",
            finalForm = "ـم",
            description = "Dudakların birbirine kapatılmasıyla çıkarılan M sesidir.",
            exampleWordArabic = "مَاء",
            exampleWordTurkish = "Su",
            audioResName = "audio_letter_mim"
        ),
        AlphabetEntity(
            orderIndex = 25,
            letterArabic = "ن",
            nameTurkish = "Nun",
            transliteration = "N",
            isolatedForm = "ن",
            initialForm = "نـ",
            medialForm = "ـنـ",
            finalForm = "ـن",
            description = "Dil ucunun üst ön diş etlerine değdirilmesiyle çıkarılan N sesidir.",
            exampleWordArabic = "نَجْم",
            exampleWordTurkish = "Yıldız",
            audioResName = "audio_letter_nun"
        ),
        AlphabetEntity(
            orderIndex = 26,
            letterArabic = "هـ",
            nameTurkish = "He",
            transliteration = "H",
            isolatedForm = "هـ",
            initialForm = "هـ",
            medialForm = "ـهـ",
            finalForm = "ـه",
            description = "Boğazın en alt kısmından (göğüsten) çıkarılan göğüs sesi ince H'dir.",
            exampleWordArabic = "هَاتِف",
            exampleWordTurkish = "Telefon",
            audioResName = "audio_letter_ha_sub"
        ),
        AlphabetEntity(
            orderIndex = 27,
            letterArabic = "و",
            nameTurkish = "Vav",
            transliteration = "W / V",
            isolatedForm = "و",
            initialForm = "وـ",
            medialForm = "ـوـ",
            finalForm = "ـو",
            description = "Dudakların yuvarlatılarak büzülmesiyle çıkarılan yumuşak V/W sesidir.",
            exampleWordArabic = "وَرْدَة",
            exampleWordTurkish = "Gül",
            audioResName = "audio_letter_waw"
        ),
        AlphabetEntity(
            orderIndex = 28,
            letterArabic = "ي",
            nameTurkish = "Ye",
            transliteration = "Y",
            isolatedForm = "ي",
            initialForm = "يـ",
            medialForm = "ـيـ",
            finalForm = "ـي",
            description = "Dil ortasının üst damağa yükseltilmesiyle çıkarılan Y sesidir.",
            exampleWordArabic = "يَد",
            exampleWordTurkish = "El",
            audioResName = "audio_letter_ya"
        )
    )

    val diacriticList = listOf(
        DiacriticEntity(
            orderIndex = 1,
            nameArabic = "فَتْحَة",
            nameTurkish = "Üstün (Fatha)",
            symbol = "َ",
            explanation = "Harfin üzerine konulan tek eğik çizgidir. İnce harflerde 'e', kalın harflerde 'a' sesi verir.",
            exampleWordArabic = "كَتَبَ",
            exampleWordTurkish = "Kataba (Yazdı)",
            soundType = "Vowel"
        ),
        DiacriticEntity(
            orderIndex = 2,
            nameArabic = "كَسْرَة",
            nameTurkish = "Esre (Kasra)",
            symbol = "ِ",
            explanation = "Harfin altına konulan tek eğik çizgidir. İnce harflerde 'i', kalın harflerde 'ı' sesine yakın okutur.",
            exampleWordArabic = "شَرِبَ",
            exampleWordTurkish = "Shariba (İçti)",
            soundType = "Vowel"
        ),
        DiacriticEntity(
            orderIndex = 3,
            nameArabic = "ضَمَّة",
            nameTurkish = "Ötre (Damma)",
            symbol = "ُ",
            explanation = "Harfin üzerine konulan küçük 'vav' benzeri işarettir. İnce harflerde 'ü', kalın harflerde 'u' sesi verir.",
            exampleWordArabic = "قُرِئَ",
            exampleWordTurkish = "Quri'a (Okundu)",
            soundType = "Vowel"
        ),
        DiacriticEntity(
            orderIndex = 4,
            nameArabic = "سُكُون",
            nameTurkish = "Cezm (Sukun)",
            symbol = "ْ",
            explanation = "Harfin üzerine konulan küçük daire sembolüdür. Harfi sessiz duraklı okutur, kendinden önceki harfe bağlar.",
            exampleWordArabic = "مِنْ",
            exampleWordTurkish = "Min (-den/-dan)",
            soundType = "Stop"
        ),
        DiacriticEntity(
            orderIndex = 5,
            nameArabic = "شَدَّة",
            nameTurkish = "Şedde (Shaddah)",
            symbol = "ّ",
            explanation = "Harfin üzerine konulan 'w' benzeri işarettir. Üzerinde bulunduğu harfi iki defa (çift) okutur.",
            exampleWordArabic = "رَبَّنَا",
            exampleWordTurkish = "Rabbena (Rabbimiz)",
            soundType = "Geminate"
        ),
        DiacriticEntity(
            orderIndex = 6,
            nameArabic = "تَنْوِين الْفَتْح",
            nameTurkish = "Üstün Tenvin (Fathatan)",
            symbol = "ً",
            explanation = "Harfin üzerine konulan iki eğik çizgidir. Kelime sonuna 'en' veya 'an' sesi ekler.",
            exampleWordArabic = "كِتَابًا",
            exampleWordTurkish = "Kitāban (Bir kitabı)",
            soundType = "Nunation"
        ),
        DiacriticEntity(
            orderIndex = 7,
            nameArabic = "تَنْوِين الْكَسْر",
            nameTurkish = "Esre Tenvin (Kasratan)",
            symbol = "ٍ",
            explanation = "Harfin altına konulan iki eğik çizgidir. Kelime sonuna 'in' veya 'ın' sesi ekler.",
            exampleWordArabic = "كِتَابٍ",
            exampleWordTurkish = "Kitābin (Bir kitabın)",
            soundType = "Nunation"
        ),
        DiacriticEntity(
            orderIndex = 8,
            nameArabic = "تَنْوِين الضَّمّ",
            nameTurkish = "Ötre Tenvin (Dammatan)",
            symbol = "ٌ",
            explanation = "Harfin üzerine konulan çift ötre sembolüdür. Kelime sonuna 'ün' veya 'un' sesi ekler.",
            exampleWordArabic = "كِتَابٌ",
            exampleWordTurkish = "Kitābun (Bir kitap)",
            soundType = "Nunation"
        )
    )

    val vocabularyList = listOf(
        // Daily Phrases
        VocabularyEntity(
            arabicText = "مَا اسْمُكَ؟",
            turkishMeaning = "Senin adın ne?",
            transliteration = "Mā ismuke?",
            category = "Daily Phrases",
            difficultyLevel = 1,
            genderNote = "Erkeklere hitap ederken",
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_ma_ismuke"
        ),
        VocabularyEntity(
            arabicText = "مَا اسْمُكِ؟",
            turkishMeaning = "Senin adın ne?",
            transliteration = "Mā ismuki?",
            category = "Daily Phrases",
            difficultyLevel = 1,
            genderNote = "Kadınlara hitap ederken",
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_ma_ismuki"
        ),
        VocabularyEntity(
            arabicText = "كَيْفَ حَالُكَ؟",
            turkishMeaning = "Nasılsın?",
            transliteration = "Kaifa ḥāluk?",
            category = "Daily Phrases",
            difficultyLevel = 1,
            genderNote = "Erkeklere hitap ederken",
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_kaifa_haluka"
        ),
        VocabularyEntity(
            arabicText = "كَيْفَ حَالُكِ؟",
            turkishMeaning = "Nasılsın?",
            transliteration = "Kaifa ḥāluki?",
            category = "Daily Phrases",
            difficultyLevel = 1,
            genderNote = "Kadınlara hitap ederken",
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_kaifa_haluki"
        ),
        VocabularyEntity(
            arabicText = "أَنَا بِخَيْرٍ",
            turkishMeaning = "İyiyim",
            transliteration = "Ana bikhair",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_ana_bikhair"
        ),
        VocabularyEntity(
            arabicText = "شُكْرًا",
            turkishMeaning = "Teşekkür ederim",
            transliteration = "Shukran",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_shukran"
        ),
        VocabularyEntity(
            arabicText = "عَفْوًا",
            turkishMeaning = "Rica ederim / Özür dilerim",
            transliteration = "Afwan",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_afwan"
        ),
        VocabularyEntity(
            arabicText = "أَهْلًا وَسَهْلًا",
            turkishMeaning = "Hoş geldiniz",
            transliteration = "Ahlan wa sahlan",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_ahlan"
        ),
        VocabularyEntity(
            arabicText = "مَعَ السَّلَامَةِ",
            turkishMeaning = "Güle güle / Selametle",
            transliteration = "Ma'as-salāmah",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_ma_assalamah"
        ),
        VocabularyEntity(
            arabicText = "صَبَاحُ الْخَيْرِ",
            turkishMeaning = "Günaydın",
            transliteration = "Sabāḥ al-khair",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_sabah_al_khair"
        ),
        VocabularyEntity(
            arabicText = "صَبَاحُ النُّورِ",
            turkishMeaning = "Günaydın (Yanıt)",
            transliteration = "Sabāḥ an-nūr",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_sabah_an_nur"
        ),
        VocabularyEntity(
            arabicText = "مَسَاءُ الْخَيْرِ",
            turkishMeaning = "İyi akşamlar",
            transliteration = "Masā' al-khair",
            category = "Daily Phrases",
            difficultyLevel = 1,
            imageResourceName = "ic_phrase",
            audioResourceName = "audio_masaa_al_khair"
        ),

        // Prayer Terms
        VocabularyEntity(
            arabicText = "صَلَاةُ الْعِشَاءِ",
            turkishMeaning = "Yatsı Namazı",
            transliteration = "Salâtü'l-İşâ",
            category = "Prayer Terms",
            difficultyLevel = 2,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_salat_al_isha"
        ),
        VocabularyEntity(
            arabicText = "صَلَاةُ الْفَجْرِ",
            turkishMeaning = "Sabah Namazı",
            transliteration = "Salâtü'l-Fajr",
            category = "Prayer Terms",
            difficultyLevel = 2,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_salat_al_fajr"
        ),
        VocabularyEntity(
            arabicText = "صَلَاةُ الظُّهْرِ",
            turkishMeaning = "Öğle Namazı",
            transliteration = "Salâtü'z-Zuhr",
            category = "Prayer Terms",
            difficultyLevel = 2,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_salat_az_zuhr"
        ),
        VocabularyEntity(
            arabicText = "صَلَاةُ الْعَصْرِ",
            turkishMeaning = "İkindi Namazı",
            transliteration = "Salâtü'l-Aṣr",
            category = "Prayer Terms",
            difficultyLevel = 2,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_salat_al_asr"
        ),
        VocabularyEntity(
            arabicText = "صَلَاةُ الْمَغْرِبِ",
            turkishMeaning = "Akşam Namazı",
            transliteration = "Salâtü'l-Maghrib",
            category = "Prayer Terms",
            difficultyLevel = 2,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_salat_al_maghrib"
        ),
        VocabularyEntity(
            arabicText = "وُضُوء",
            turkishMeaning = "Abdest",
            transliteration = "Wuḍū'",
            category = "Prayer Terms",
            difficultyLevel = 1,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_wudu"
        ),
        VocabularyEntity(
            arabicText = "مَسْجِد",
            turkishMeaning = "Cami / Mescit",
            transliteration = "Masjid",
            category = "Prayer Terms",
            difficultyLevel = 1,
            pluralArabic = "مَسَاجِد",
            pluralTurkish = "Mescitler",
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_masjid"
        ),
        VocabularyEntity(
            arabicText = "قِبْلَة",
            turkishMeaning = "Kıble",
            transliteration = "Qiblah",
            category = "Prayer Terms",
            difficultyLevel = 1,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_qiblah"
        ),
        VocabularyEntity(
            arabicText = "دُعَاء",
            turkishMeaning = "Dua",
            transliteration = "Du'ā'",
            category = "Prayer Terms",
            difficultyLevel = 1,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_dua"
        ),
        VocabularyEntity(
            arabicText = "أَذَان",
            turkishMeaning = "Ezan",
            transliteration = "Adhān",
            category = "Prayer Terms",
            difficultyLevel = 1,
            imageResourceName = "ic_prayer",
            audioResourceName = "audio_adhan"
        ),

        // Everyday Objects
        VocabularyEntity(
            arabicText = "كِتَاب",
            turkishMeaning = "Kitap",
            transliteration = "Kitāb",
            category = "Everyday Objects",
            difficultyLevel = 1,
            pluralArabic = "كُتُب",
            pluralTurkish = "Kitaplar",
            imageResourceName = "ic_book",
            audioResourceName = "audio_kitab"
        ),
        VocabularyEntity(
            arabicText = "قَلَم",
            turkishMeaning = "Kalem",
            transliteration = "Qalam",
            category = "Everyday Objects",
            difficultyLevel = 1,
            pluralArabic = "أَقْلَام",
            pluralTurkish = "Kalemler",
            imageResourceName = "ic_pen",
            audioResourceName = "audio_qalam"
        ),
        VocabularyEntity(
            arabicText = "بَاب",
            turkishMeaning = "Kapı",
            transliteration = "Bāb",
            category = "Everyday Objects",
            difficultyLevel = 1,
            pluralArabic = "أَبْوَاب",
            pluralTurkish = "Kapılar",
            imageResourceName = "ic_door",
            audioResourceName = "audio_bab"
        ),
        VocabularyEntity(
            arabicText = "بَيْت",
            turkishMeaning = "Ev",
            transliteration = "Bait",
            category = "Everyday Objects",
            difficultyLevel = 1,
            pluralArabic = "بُيُوت",
            pluralTurkish = "Evler",
            imageResourceName = "ic_house",
            audioResourceName = "audio_bait"
        ),
        VocabularyEntity(
            arabicText = "طَاوِلَة",
            turkishMeaning = "Masa",
            transliteration = "Ṭāwilah",
            category = "Everyday Objects",
            difficultyLevel = 1,
            imageResourceName = "ic_table",
            audioResourceName = "audio_tawilah"
        ),
        VocabularyEntity(
            arabicText = "كُرْسِيّ",
            turkishMeaning = "Sandalye",
            transliteration = "Kursī",
            category = "Everyday Objects",
            difficultyLevel = 1,
            imageResourceName = "ic_chair",
            audioResourceName = "audio_kursi"
        ),
        VocabularyEntity(
            arabicText = "نَافِذَة",
            turkishMeaning = "Pencere",
            transliteration = "Nāfidhah",
            category = "Everyday Objects",
            difficultyLevel = 2,
            imageResourceName = "ic_window",
            audioResourceName = "audio_nafidhah"
        ),
        VocabularyEntity(
            arabicText = "هَاتِف",
            turkishMeaning = "Telefon",
            transliteration = "Hātef",
            category = "Everyday Objects",
            difficultyLevel = 1,
            imageResourceName = "ic_phone",
            audioResourceName = "audio_hatef"
        ),
        VocabularyEntity(
            arabicText = "سَاعَة",
            turkishMeaning = "Saat",
            transliteration = "Sā'ah",
            category = "Everyday Objects",
            difficultyLevel = 1,
            imageResourceName = "ic_clock",
            audioResourceName = "audio_saah"
        ),

        // Animals
        VocabularyEntity(
            arabicText = "أَسَد",
            turkishMeaning = "Aslan",
            transliteration = "Asad",
            category = "Animals",
            difficultyLevel = 1,
            imageResourceName = "ic_lion",
            audioResourceName = "audio_asad"
        ),
        VocabularyEntity(
            arabicText = "قِطّ",
            turkishMeaning = "Kedi",
            transliteration = "Qiṭṭ",
            category = "Animals",
            difficultyLevel = 1,
            imageResourceName = "ic_cat",
            audioResourceName = "audio_qitt"
        ),
        VocabularyEntity(
            arabicText = "كَلْب",
            turkishMeaning = "Köpek",
            transliteration = "Kalb",
            category = "Animals",
            difficultyLevel = 1,
            imageResourceName = "ic_dog",
            audioResourceName = "audio_kalb"
        ),
        VocabularyEntity(
            arabicText = "جَمَل",
            turkishMeaning = "Deve",
            transliteration = "Jamal",
            category = "Animals",
            difficultyLevel = 1,
            imageResourceName = "ic_camel",
            audioResourceName = "audio_jamal"
        ),
        VocabularyEntity(
            arabicText = "طَائِر",
            turkishMeaning = "Kuş",
            transliteration = "Ṭā'ir",
            category = "Animals",
            difficultyLevel = 1,
            imageResourceName = "ic_bird",
            audioResourceName = "audio_tair"
        ),
        VocabularyEntity(
            arabicText = "سَمَك",
            turkishMeaning = "Balık",
            transliteration = "Samak",
            category = "Animals",
            difficultyLevel = 1,
            imageResourceName = "ic_fish",
            audioResourceName = "audio_samak"
        ),
        VocabularyEntity(
            arabicText = "فِيل",
            turkishMeaning = "Fil",
            transliteration = "Fīl",
            category = "Animals",
            difficultyLevel = 1,
            imageResourceName = "ic_elephant",
            audioResourceName = "audio_fil"
        ),

        // Numbers
        VocabularyEntity(
            arabicText = "وَاحِد",
            turkishMeaning = "Bir (1)",
            transliteration = "Wāḥid",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_wahid"
        ),
        VocabularyEntity(
            arabicText = "اثْنَان",
            turkishMeaning = "İki (2)",
            transliteration = "Ithnān",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_ithnan"
        ),
        VocabularyEntity(
            arabicText = "ثَلَاثَة",
            turkishMeaning = "Üç (3)",
            transliteration = "Thalāthah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_thalathah"
        ),
        VocabularyEntity(
            arabicText = "أَرْبَعَة",
            turkishMeaning = "Dört (4)",
            transliteration = "Arba'ah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_arbaah"
        ),
        VocabularyEntity(
            arabicText = "خَمْسَة",
            turkishMeaning = "Beş (5)",
            transliteration = "Khamsah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_khamsah"
        ),
        VocabularyEntity(
            arabicText = "سِتَّة",
            turkishMeaning = "Altı (6)",
            transliteration = "Sittah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_sittah"
        ),
        VocabularyEntity(
            arabicText = "سَبْعَة",
            turkishMeaning = "Yedi (7)",
            transliteration = "Sab'ah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_sabah"
        ),
        VocabularyEntity(
            arabicText = "ثَمَانِيَة",
            turkishMeaning = "Sekiz (8)",
            transliteration = "Thamāniyah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_thamaniyah"
        ),
        VocabularyEntity(
            arabicText = "تِسْعَة",
            turkishMeaning = "Dokuz (9)",
            transliteration = "Tis'ah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_tisah"
        ),
        VocabularyEntity(
            arabicText = "عَشَرَة",
            turkishMeaning = "On (10)",
            transliteration = "Asharah",
            category = "Numbers",
            difficultyLevel = 1,
            imageResourceName = "ic_number",
            audioResourceName = "audio_asharah"
        )
    )

    val quizQuestionList = listOf(
        // Placement Test Questions
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 1,
            questionText = "'مَا اسْمُكِ؟' (Mā ismuki?) ifadesi hangi durumda kullanılır?",
            questionArabic = "مَا اسْمُكِ؟",
            optionA = "Bir erkeğe adını sorarken",
            optionB = "Bir kadına adını sorarken",
            optionC = "Teşekkür ederken",
            optionD = "Vedalaşırken",
            correctOptionIndex = 1,
            explanation = "'Mā ismuki?' sonundaki 'ki' zamiri sebebiyle kadınlara hitap ederken 'Senin adın ne?' anlamına gelir."
        ),
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 1,
            questionText = "Arapçada 'Yatsı Namazı' teriminin karşılığı hangisidir?",
            questionArabic = "صَلَاةُ الْعِشَاءِ",
            optionA = "Salâtü'l-Fajr",
            optionB = "Salâtü'z-Zuhr",
            optionC = "Salâtü'l-Aṣr",
            optionD = "Salâtü'l-İşâ",
            correctOptionIndex = 3,
            explanation = "'صَلَاةُ الْعِشَاءِ' (Salâtü'l-İşâ) Yatsı Namazı demektir."
        ),
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 1,
            questionText = "Peltik ve ince okunan harf hangisidir?",
            questionArabic = "ث",
            optionA = "ت (Te)",
            optionB = "ث (Tha/Se)",
            optionC = "ط (Tı)",
            optionD = "ص (Sad)",
            correctOptionIndex = 1,
            explanation = "ث (Tha) harfi dil ucunun ön dişler arasına hafifçe sıkıştırılmasıyla çıkarılan peltik ince bir sestir."
        ),
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 1,
            questionText = "Harfin üzerine konulan ve harfi çift okutan hareke hangisidir?",
            questionArabic = "ّ",
            optionA = "Fatha (Üstün)",
            optionB = "Sukun (Cezm)",
            optionC = "Shaddah (Şedde)",
            optionD = "Kasra (Esre)",
            correctOptionIndex = 2,
            explanation = "Şedde (ّ) üzerine konduğu harfi çift okutur."
        ),
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 2,
            questionText = "'كِتَاب' (Kitāb) kelimesinin Türkçe karşılığı nedir?",
            questionArabic = "كِتَاب",
            optionA = "Kalem",
            optionB = "Defter",
            optionC = "Kitap",
            optionD = "Masa",
            correctOptionIndex = 2,
            explanation = "'كِتَاب' Türkçe 'Kitap' demektir."
        ),
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 2,
            questionText = "'شُكْرًا' (Shukran) ne anlama gelir?",
            questionArabic = "شُكْرًا",
            optionA = "Günaydın",
            optionB = "Teşekkür ederim",
            optionC = "Güle güle",
            optionD = "İyiyim",
            correctOptionIndex = 1,
            explanation = "'Shukran' Arapça teşekkür ederim demektir."
        ),
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 2,
            questionText = "Arapçada 'ثَلَاثَة' (Thalāthah) sayısı kaça karşılık gelir?",
            questionArabic = "ثَلَاثَة",
            optionA = "1",
            optionB = "2",
            optionC = "3",
            optionD = "4",
            correctOptionIndex = 2,
            explanation = "Thalāthah Arapçada 3 (üç) anlamına gelir."
        ),
        QuizQuestionEntity(
            quizType = "PLACEMENT",
            category = "Placement Test",
            difficultyLevel = 3,
            questionText = "'أَسَد' (Asad) kelimesinin anlamı nedir?",
            questionArabic = "أَسَد",
            optionA = "Kedi",
            optionB = "Köpek",
            optionC = "Aslan",
            optionD = "Kuş",
            correctOptionIndex = 2,
            explanation = "'Asad' Arapça aslan demektir."
        ),

        // Daily Progressive Quizzes (Level 1 - Easy)
        // Type 1: Arabic to Turkish Translation
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "ARABIC_TO_TURKISH",
            difficultyLevel = 1,
            questionText = "'كِتَاب' (Kitāb) kelimesinin Türkçe karşılığı nedir?",
            questionArabic = "كِتَاب",
            optionA = "Kalem",
            optionB = "Kitap",
            optionC = "Masa",
            optionD = "Defter",
            correctOptionIndex = 1,
            explanation = "'كِتَاب' Türkçe 'Kitap' anlamına gelir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "ARABIC_TO_TURKISH",
            difficultyLevel = 1,
            questionText = "'شُكْرًا' (Shukran) ifadesinin Türkçe karşılığı nedir?",
            questionArabic = "شُكْرًا",
            optionA = "Rica ederim",
            optionB = "Teşekkür ederim",
            optionC = "Günaydın",
            optionD = "Hoşça kal",
            correctOptionIndex = 1,
            explanation = "'شُكْرًا' (Shukran) Arapçada teşekkür ederim demektir."
        ),

        // Type 2: Turkish to Arabic Translation
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "TURKISH_TO_ARABIC",
            difficultyLevel = 1,
            questionText = "Türkçesi 'Kalem' olan kelimenin Arapçası hangisidir?",
            questionArabic = null,
            optionA = "قَلَم",
            optionB = "كِتَاب",
            optionC = "بَيْت",
            optionD = "مَسْجِد",
            correctOptionIndex = 0,
            explanation = "'Kalem' Arapçada 'قَلَم' (Qalam) olarak yazılır."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "TURKISH_TO_ARABIC",
            difficultyLevel = 1,
            questionText = "Türkçesi 'Kedi' olan hayvan isminin Arapçası hangisidir?",
            questionArabic = null,
            optionA = "كَلْب",
            optionB = "قِطّ",
            optionC = "جَمَل",
            optionD = "طَائِر",
            correctOptionIndex = 1,
            explanation = "'Kedi' Arapçada 'قِطّ' (Qiṭṭ) demektir."
        ),

        // Type 3: Visual Card / Illustration to Arabic Matching
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "VISUAL_MATCHING",
            difficultyLevel = 1,
            questionText = "Görseldeki Mescit / Cami kavramının Arapça karşılığı hangisidir?",
            questionArabic = "مَسْجِد",
            optionA = "مَسْجِد",
            optionB = "مَدْرَسَة",
            optionC = "بَيْت",
            optionD = "سُوق",
            correctOptionIndex = 0,
            explanation = "Görseldeki ibadethane 'مَسْجِد' (Masjid) kelimesidir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "VISUAL_MATCHING",
            difficultyLevel = 1,
            questionText = "Görseldeki 'Ev' simgesinin Arapça karşılığı hangisidir?",
            questionArabic = "بَيْت",
            optionA = "بَاب",
            optionB = "بَيْت",
            optionC = "مَكْتَب",
            optionD = "غُرْفَة",
            correctOptionIndex = 1,
            explanation = "Görseldeki ev 'بَيْت' (Bayt) kelimesidir."
        ),

        // Type 4: Fill in missing diacritic/letter or Transliteration matching
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "DIACRITIC_TRANSLITERATION",
            difficultyLevel = 1,
            questionText = "'قَـ_ـم' (Kalem) kelimesinde boş bırakılan yere hangi harf gelmelidir?",
            questionArabic = "قَـ_ـم",
            optionA = "ل (Lām)",
            optionB = "م (Mīm)",
            optionC = "ب (Bā')",
            optionD = "ك (Kāf)",
            correctOptionIndex = 0,
            explanation = "Kalem kelimesi 'قَلَم' şeklinde yazılır, ortadaki harf 'ل' (Lām)'dır."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "DIACRITIC_TRANSLITERATION",
            difficultyLevel = 1,
            questionText = "İnce harflerde 'i', kalın harflerde 'ı' sesi veren alt hareke hangisidir?",
            questionArabic = "ِ",
            optionA = "Fatha (Üstün)",
            optionB = "Kasra (Esre)",
            optionC = "Damma (Ötre)",
            optionD = "Sukun (Cezm)",
            correctOptionIndex = 1,
            explanation = "Kasra (Esre) harfin altına konur ve 'i' / 'ı' sesi verir."
        ),

        // Level 2 (Medium / Level 2)
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "ARABIC_TO_TURKISH",
            difficultyLevel = 2,
            questionText = "'مَدْرَسَة' (Madrasah) kelimesinin anlamı nedir?",
            questionArabic = "مَدْرَسَة",
            optionA = "Hastane",
            optionB = "Okul",
            optionC = "Kütüphane",
            optionD = "Mutfak",
            correctOptionIndex = 1,
            explanation = "'مَدْرَسَة' okul demektir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "TURKISH_TO_ARABIC",
            difficultyLevel = 2,
            questionText = "Türkçe 'Öğretmen' kelimesinin Arapçası hangisidir?",
            questionArabic = null,
            optionA = "طَالِب",
            optionB = "مُعَلِّم",
            optionC = "طَبِيب",
            optionD = "مُهَنْدِس",
            correctOptionIndex = 1,
            explanation = "'Öğretmen' Arapçada 'مُعَلِّم' (Mu'allim) demektir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "VISUAL_MATCHING",
            difficultyLevel = 2,
            questionText = "Görseldeki 'Kitap' simgesinin Arapça karşılığı hangisidir?",
            questionArabic = "كِتَاب",
            optionA = "قَلَم",
            optionB = "كِتَاب",
            optionC = "دَفْتَر",
            optionD = "مَكْتَبَة",
            correctOptionIndex = 1,
            explanation = "Görseldeki el yazması kitap 'كِتَاب' kelimesidir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "DIACRITIC_TRANSLITERATION",
            difficultyLevel = 2,
            questionText = "'Sabāḥ al-khair' (Günaydın) transliterasyonunun Arapça yazılışı hangisidir?",
            questionArabic = "صَبَاحُ الْخَيْرِ",
            optionA = "صَبَاحُ الْخَيْرِ",
            optionB = "مَسَاءُ الْخَيْرِ",
            optionC = "مَعَ السَّلَامَةِ",
            optionD = "كَيْفَ حَالُكَ",
            correctOptionIndex = 0,
            explanation = "'Sabāḥ al-khair' okunuşu 'صَبَاحُ الْخَيْرِ' şeklindedir."
        ),

        // Level 3 (Hard / Level 3)
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "ARABIC_TO_TURKISH",
            difficultyLevel = 3,
            questionText = "'مَسْؤُولِيَّة' kelimesinin Türkçe karşılığı nedir?",
            questionArabic = "مَسْؤُولِيَّة",
            optionA = "Özgürlük",
            optionB = "Sorumluluk",
            optionC = "Adalet",
            optionD = "Eşitlik",
            correctOptionIndex = 1,
            explanation = "'مَسْؤُولِيَّة' (Mas'ūliyyah) Arapça sorumluluk anlamına gelir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "TURKISH_TO_ARABIC",
            difficultyLevel = 3,
            questionText = "Türkçesi 'Yazma Eser / Elyazması' olan kavramın Arapçası hangisidir?",
            questionArabic = null,
            optionA = "كِتَاب",
            optionB = "مَخْطُوطَة",
            optionC = "صَحِيفَة",
            optionD = "قَامُوس",
            correctOptionIndex = 1,
            explanation = "'Yazma Eser' Arapçada 'مَخْطُوطَة' (Makhtūṭah) kelimesidir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "VISUAL_MATCHING",
            difficultyLevel = 3,
            questionText = "Görseldeki 'Âlim / Bilgin' kavramının Arapça karşılığı hangisidir?",
            questionArabic = "عَالِم",
            optionA = "طَالِب",
            optionB = "عَالِم",
            optionC = "كَاتِب",
            optionD = "قَاضِي",
            correctOptionIndex = 1,
            explanation = "Görseldeki bilgin/âlim 'عَالِم' kelimesidir."
        ),
        QuizQuestionEntity(
            quizType = "DAILY",
            category = "DIACRITIC_TRANSLITERATION",
            difficultyLevel = 3,
            questionText = "Harfi çift okutan Şedde (ّ) harekesi aşağıdaki hangi kelimede yer alır?",
            questionArabic = "مُعَلِّم",
            optionA = "كِتَاب",
            optionB = "مُعَلِّم",
            optionC = "قَلَم",
            optionD = "مَسْجِد",
            correctOptionIndex = 1,
            explanation = "'مُعَلِّم' (Mu'allim) kelimesinde Lām harfi şeddelidir."
        )
    )
}
