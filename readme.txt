	Piętnastka – układanka zbudowana z pudełka,
 w którym znajduje się 15 kwadratowych klocków o jednakowych rozmiarach ułożonych
 w kwadrat 4×4 i ponumerowanych od 1 do 15(w standardowej wersji ponieważ program potrafi także rozwiązywać układanke o dowolnym rozmiarze). Jedno miejsce jest puste i umożliwia
 przesuwanie sąsiednich klocków względem siebie. Uważana jest za pierwowzór kostki Rubika.
 
 Celem gry jest ułożenie klocków w określonej kolejności od 1 do 15
 
 
 program potrafi znaleźć rozwiązanie(jeśli takie istnieje) poprzez wywołanie jdnego z czterech zaimplementowanych algorytmów
 BFS, DFS, A*-hamming, A*-manhattan
 
 
 Program działa w trybie wsadowym i jako argumenty przyjmuje kolejno
 [0] - nazwe algorytmu który ma być wywołany("bfs","dfs","astr")
 [1] - dla bfs i dfs kolejność przeszukiwania np. "RDUL" - prawo dół góra lewo, a dla astr określenie hurystyki : "manh" lub "hamm"
 [2] - ścieżkę do pliku z początkowym stanem układanki($ścieżka\zbiory\4x4_03_0001.txt)
 [3] - ścieżkę do pliku do którego ma zostać zapisane rozwiązanie(długość rozwiązania oraz wykonane ruchy)
 [4] - ścieżkę do pliku do którego mają zostać zapisane statystyki