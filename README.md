# MultithreadedTaskFlow
recruitment task - 1

Zadanie nr 1

Zbuduj aplikację, która będzie składać się z dwóch umownych komponentów:
TaskProducer
TaskConsumer
(przez umownych rozumiem fakt, że mogą być takie beany w aplikacji, ale może to być też zestaw funkcjonalny kilku klas wykonujący tę funkcję). Obydwa uruchamiane są na starcie aplikacji i współdzielą globalną kolejkę (opartą na liście, queue, do indywidualnej decyzji programisty), do której TaskProducer generuje i wrzuca losowe zadania (obiekt typu Task, co to znaczy zadanie wyjaśnione poniżej), aż się dana kolejka zapełni (tzn. osiągnie konfigurowalny maks. limit określony jakąś stałą, parametrem konfigurowalnym w aplikacji itd.). Jak kolejka się zapełni to TaskProducer czeka (okresowo próbkując stan kolejki), aż jej wielkość spadnie do połowy i ponownie zapełnia i tak w kółko.
W tym czasie TaskConsumer pobiera z kolejki zadanie (o ile jakieś zadanie w kolejce jest, jak nie ma to okresowo próbkuje kolejkę czekając na zadania) i jeśli jest zadanie to je realizuje, a wynik zadania wypisuje na konsolę.
Co to jest zadanie? Zadanie to wygenerowany losowo ciąg znakowy z zakresu "0-9+/*-" (np.: 5+4, 323/65323*24323/2343+2234-2233), który analizuje ten ciąg, dokonuje obliczenia wg wzoru i wypisuje wynik na konsolę.

Dodatkowo, proszę stworzyć min. 2 TaskProducers pracujące w osobnych wątkach oraz min. 4 TaskConsumers, także pracujących w osobnych wątkach.

Aby ułatwić sprawdzenie poprawności należy przygotować chociaż jeden test jednostkowy przykładowego TaskConsumera, któremu podamy przykładowy ciąg znaków. Test jednostkowy może mieć formę dowolną, to może być junit, ale może być też zwykła aplikacja main, która bierze z konsoli ciąg znaków i wywołuje metodę (tutaj pełna dowolność).

Środowisko developerskie w miarę dowolne, jak będzie wygodnie programiście: Eclipse, Netbeans, linia poleceń. Może być kompilowane via Maven albo inne dowolne środowisko, choć to nie wydaje się konieczne (brak zewnętrznych komponentów).
