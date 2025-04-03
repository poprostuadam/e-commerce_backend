# 🛒 E-commerce Backend - Demo

## 📌 Opis projektu
Projekt obejmuje implementację backendu wersji demonstracyjnej dla platformy e-commerce, umożliwiającej klientom supermarketu zakupy online.

## 📂 Zawartość systemu
System obsługuje następujące funkcjonalności:

### 1. Produkt
- Każdy produkt posiada:
  - Nazwę
  - Cenę
  - Kategorię
  - Status dostępności (dostępny/niedostępny do zakupu)

### 2. Katalog produktów
- Katalog zawiera wszystkie produkty dostępne w sklepie.
- Przy uruchomieniu aplikacji katalog jest uzupełniany o predefiniowane produkty.
- Katalog pozwala na:
  - Pobieranie i wyświetlanie wszystkich produktów (posortowane alfabetycznie)
  - Pobieranie i wyświetlanie produktów danej kategorii (posortowane od najtańszych do najdroższych, wyłącznie dostępne produkty)

### 3. Koszyk zakupów
- Domyślnie koszyk jest pusty.
- Można dodawać produkty do koszyka (także wielokrotnie ten sam produkt).
- Można usuwać produkty z koszyka.
- Można wyświetlać produkty w koszyku oraz ich liczbę.
- Koszyk umożliwia obliczenie i wyświetlenie całkowitej ceny produktów w nim zawartych.

### 4. Diagram klas
- Projekt zawiera diagram klas przedstawiający implementowane encje i ich zależności.
- Diagram można stworzyć np. przy użyciu Excalidraw.

### 5. ⭐ Obsługa promocji
- Można dodać kod rabatowy do koszyka.
- Każda kolejna aktywowana promocja nadpisuje poprzednią.
- Obsługiwane promocje:
  1. **10% rabatu na całe zamówienie** – wartość koszyka obniżana o 10%.
  2. **Najtańszy produkt za 1 zł przy zakupie 3 produktów** – co 3 produkty, najtańszy kosztuje 1 zł.
  3. **Drugi taki sam produkt za połowę ceny** – co 2 takie same produkty, jeden kosztuje 50% ceny.

### 6. ⭐ Testy jednostkowe
- Przetestowano 2-3 funkcjonalności jednostkowo za pomocą **JUnit5**.
- Testy zostały napisane zgodnie z techniką **given-when-then**.

## 🔧 Technologie
Projekt został zrealizowany w języku **Java** i wykorzystuje:
- **JUnit5** do testów jednostkowych
- **Maven/Gradle** do zarządzania zależnościami

## 📌 Instalacja i uruchomienie
1. **Klonowanie repozytorium**:
   ```bash
   git clone https://github.com/TwojeRepozytorium/ecommerce-backend.git
   cd ecommerce-backend
   ```
2. **Uruchomienie aplikacji**:
   ```bash
   ./gradlew bootRun
   ```

## 🚀 Funkcjonalności
- Zarządzanie katalogiem produktów
- Obsługa koszyka zakupów
- Implementacja promocji
- Testy jednostkowe

## 🤝 Współpraca
Chcesz pomóc w rozwoju projektu? Zapraszamy do zgłaszania błędów, propozycji zmian oraz pull requestów!

## 📜 Licencja
Projekt jest dostępny na licencji MIT. Więcej informacji znajdziesz w pliku `LICENSE`.

---
📧 **Kontakt:** Jeśli masz pytania dotyczące projektu, skontaktuj się poprzez GitHub Issues lub e-mail.

