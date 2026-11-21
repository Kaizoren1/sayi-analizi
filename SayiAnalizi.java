import java.util.Scanner;

public class SayiAnalizi {

    public static void main(String[] args) {
        Scanner klavye = new Scanner(System.in);

        // Sayaclar ve toplamlar (dizi kullanmiyoruz, her sey degiskende)
        int adet = 0;
        int pozitifAdet = 0;
        int negatifAdet = 0;
        int tekAdet = 0;
        int ciftAdet = 0;
        int asalAdet = 0;
        long toplam = 0;
        long pozitifToplam = 0;
        int enBuyuk = 0;
        int enKucuk = 0;

        System.out.println("=== SAYI ANALIZ PROGRAMI ===");
        System.out.println("Sayilari girin. Bitirmek icin 0 girin.");

        while (true) {
            System.out.print("Sayi: ");

            // Kullanici sayi yerine harf yazarsa uyar ve tekrar sor
            if (!klavye.hasNextInt()) {
                String yanlis = klavye.next();
                System.out.println("  Uyari: \"" + yanlis + "\" bir tam sayi degil, tekrar deneyin.");
                continue;
            }

            int sayi = klavye.nextInt();

            // 0 girilirse donguden cik
            if (sayi == 0) {
                break;
            }

            adet++;
            toplam = toplam + sayi;

            // Ilk sayi hem en buyuk hem en kucuk olur
            if (adet == 1) {
                enBuyuk = sayi;
                enKucuk = sayi;
            } else {
                if (sayi > enBuyuk) {
                    enBuyuk = sayi;
                }
                if (sayi < enKucuk) {
                    enKucuk = sayi;
                }
            }

            // Pozitif mi negatif mi?
            if (sayi > 0) {
                pozitifAdet++;
                pozitifToplam = pozitifToplam + sayi;
            } else {
                negatifAdet++;
            }

            // Tek mi cift mi?
            if (sayi % 2 == 0) {
                ciftAdet++;
            } else {
                tekAdet++;
            }

            // Ozel sayi analizi
            boolean asal = asalMi(sayi);
            boolean mukemmel = mukemmelMi(sayi);
            int basamak = basamakSayisi(sayi);

            if (asal) {
                asalAdet++;
            }

            System.out.println("  -> " + sayi
                    + " | Asal: " + (asal ? "Evet" : "Hayir")
                    + " | Mukemmel: " + (mukemmel ? "Evet" : "Hayir")
                    + " | Basamak sayisi: " + basamak);
        }

        // Hic sayi girilmediyse uyar ve bitir
        if (adet == 0) {
            System.out.println("Uyari: Hic sayi girmediniz, analiz yapilamadi.");
            klavye.close();
            return;
        }

        double ortalama = (double) toplam / adet;

        System.out.println();
        System.out.println("========= SONUC RAPORU =========");
        System.out.println("Girilen sayi adedi     : " + adet);
        System.out.println("Pozitif sayi adedi     : " + pozitifAdet);
        System.out.println("Negatif sayi adedi     : " + negatifAdet);
        System.out.println("Tek sayi adedi         : " + tekAdet);
        System.out.println("Cift sayi adedi        : " + ciftAdet);
        System.out.println("En buyuk sayi          : " + enBuyuk);
        System.out.println("En kucuk sayi          : " + enKucuk);
        System.out.println("Toplam                 : " + toplam);
        System.out.printf("Aritmetik ortalama     : %.2f%n", ortalama);
        System.out.println("--- Ek Gorevler ---");
        System.out.println("En buyuk - En kucuk    : " + ((long) enBuyuk - enKucuk));

        if (pozitifAdet > 0) {
            double pozitifOrtalama = (double) pozitifToplam / pozitifAdet;
            System.out.printf("Pozitif ortalama       : %.2f%n", pozitifOrtalama);
        } else {
            System.out.println("Pozitif ortalama       : Pozitif sayi girilmedi");
        }

        System.out.println("Asal sayi adedi        : " + asalAdet);
        System.out.println("================================");

        klavye.close();
    }

    // Asal sayi: 1'den buyuk ve sadece 1'e ve kendisine bolunen sayi
    public static boolean asalMi(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Mukemmel sayi: kendisi haric bolenlerinin toplami kendisine esit (6 = 1+2+3)
    public static boolean mukemmelMi(int n) {
        if (n < 2) {
            return false;
        }
        int bolenToplami = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                bolenToplami = bolenToplami + i;
            }
        }
        return bolenToplami == n;
    }

    // Basamak sayisi: sayiyi 0 olana kadar 10'a bol, her bolmeyi say
    public static int basamakSayisi(int n) {
        int basamak = 0;
        do {
            n = n / 10;
            basamak++;
        } while (n != 0);
        return basamak;
    }
}
