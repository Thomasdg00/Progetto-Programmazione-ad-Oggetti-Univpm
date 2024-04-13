import java.util.Scanner;
import java.util.List;
import java.util.*;


public class Progetto {
    public static void main(String[] args){
        //Inizializzazione Scanner
        Scanner scanner  = new Scanner(System.in);
        String[] info = scanner.nextLine().split(" ");

        //Inizializzazione variabili
        int numeroAerei = Integer.parseInt(info[0]);
        int numeroAeroporti = Integer.parseInt(info[1]);
        int numeroViaggi = Integer.parseInt(info[2]);

        //Lettura dei dati Aeromobile
        List<Aeromobile> listaAeromobili = new ArrayList<>();
        for(int i = 0; i < numeroAerei; i++){
            String[] infoAereo = scanner.nextLine().split(" ");
            String idAereo = infoAereo[0];
            int numeroMaxPasseggeri = Integer.parseInt(infoAereo[1]);
            int capienzaSerbatoio = Integer.parseInt(infoAereo[2]);
            int viaggiUltimoAnno = Integer.parseInt(infoAereo[3]);

            listaAeromobili.add(new Aeromobile(idAereo, numeroMaxPasseggeri, capienzaSerbatoio, viaggiUltimoAnno));
        }

        //Lettura dei dati Aeroporto
        List<Aeroporto> listaAeroporti = new ArrayList<>();
        for(int i = 0; i < numeroAeroporti; i++){
            String[] infoAeroporti = scanner.nextLine().split(",");
            String codiceAeroporto = infoAeroporti[0];
            String nazionalita = infoAeroporti[1];
            int numeroMaxDiAeroporti = Integer.parseInt(infoAeroporti[2]);

            listaAeroporti.add(new Aeroporto(codiceAeroporto, nazionalita, numeroMaxDiAeroporti));
        }

        //Lettura dei dati Viaggio
        List<Viaggio> listaViaggi = new ArrayList<>();
        for(int i = 0; i < numeroViaggi; i++){
            String[] infoViaggio = scanner.nextLine().split(" ");
            String nomeAereo = infoViaggio[0];
            int numeroPasseggeri = Integer.parseInt(infoViaggio[1]);
            String aeroportoDiPartenza = infoViaggio[2];
            String aeroportoDiArrivo = infoViaggio[3];
            int giornoDelViaggio = Integer.parseInt(infoViaggio[4]);

            listaViaggi.add(new Viaggio(nomeAereo, numeroPasseggeri, aeroportoDiPartenza, aeroportoDiArrivo, giornoDelViaggio));
        }

        //Lettura del task
        String[] infoTask = scanner.nextLine().split(" ");

        //Scelta del task da eseguire
        switch (infoTask[0]) {
            case "TASK1" -> task1(listaAeromobili, listaAeroporti, listaViaggi);
            case "TASK2" -> {
                int p = Integer.parseInt(infoTask[1]);
                int q = Integer.parseInt(infoTask[2]);
                task2(listaAeromobili, listaAeroporti, listaViaggi, p, q);
            }
            case "TASK3" -> {
                int l = Integer.parseInt(infoTask[1]);
                List<Viaggio> nuovaListaViaggi = new ArrayList<>();
                for (int i = 0; i < l; i++) {
                    String[] infoNuovoViaggio = scanner.nextLine().split(" ");
                    String nuovoCodiceAereo = infoNuovoViaggio[0];
                    int nuovoNumeroPasseggeri = Integer.parseInt(infoNuovoViaggio[1]);
                    String nuovoAeroportoDiPartenza = infoNuovoViaggio[2];
                    String nuovoAeroportoDiArrivo = infoNuovoViaggio[3];
                    int nuovoGiornoDelVolo = Integer.parseInt(infoNuovoViaggio[4]);

                    nuovaListaViaggi.add(new Viaggio(nuovoCodiceAereo, nuovoNumeroPasseggeri, nuovoAeroportoDiPartenza, nuovoAeroportoDiArrivo, nuovoGiornoDelVolo));
                }
                task3(listaAeromobili, listaAeroporti, listaViaggi, nuovaListaViaggi);
            }
        }
    }


    public static void task1(List<Aeromobile> listaAeromobili, List<Aeroporto> listaAeroporti, List<Viaggio> listaViaggi){
        //Stampa il numero di aeromobili, di aeroporti e di viaggi
        System.out.println(listaAeromobili.size() + " " + listaAeroporti.size() + " " + listaViaggi.size());

        //Stampa il numero di viaggi underboard e overboard
        int viaggiUnderboard = 0, viaggiOverboard = 0;
        for(Viaggio viaggio : listaViaggi){
            String tipoViaggio;
            for (Aeromobile aereo : listaAeromobili){
                if(viaggio.getAeromobile().equals(aereo.getId())){
                    tipoViaggio = String.valueOf(viaggio.getTipoViaggio(aereo));
                    switch (tipoViaggio) {
                        case "UNDERBOARD" -> viaggiUnderboard++;
                        case "OVERBOARD" -> viaggiOverboard++;
                    }
                }
            }
        }
        System.out.println(viaggiOverboard + " " + viaggiUnderboard);

        //Stampa il codice dell'aeroporto con più aerei da gestire
        List<String> listaAeroportiOverboard = getStringList(listaAeroporti);
        listaAeroportiOverboard.sort(null);
        if(!listaAeroportiOverboard.isEmpty()){
            for(String aeroporto : listaAeroportiOverboard){
                System.out.println(aeroporto);
            }
        } else {
            System.out.println();
        }

        //Stampare il numero di aeromobili con serbatoio maggiore di 108 litri
        int numeroDiAeromobili = 0;
        for(Aeromobile aeromobile: listaAeromobili){
            if(aeromobile.getSerbatoioMax() > 108){
                numeroDiAeromobili++;
            }
        }
        System.out.println(numeroDiAeromobili);

        //Stampare il giorno con il numero di viaggi effettuati più alto
        int numeroGiorno = 0, numeroViaggiInUnGiorno = 0;
        for(int i = 1; i < 366; i++){
            int contatoreViaggiGiorno = 0;
            for(Viaggio viaggio : listaViaggi){
                if(viaggio.getGiornoDelVolo() == i){
                    contatoreViaggiGiorno++;
                }
            }
            if(contatoreViaggiGiorno > numeroViaggiInUnGiorno){
                numeroGiorno = i;
                numeroViaggiInUnGiorno = contatoreViaggiGiorno;
            }
        }
        System.out.println(numeroGiorno);

        //Stampare il numero di aeromobili con esattamente 42 viaggi effettuati nell'ultimo anno
        int numeroAereiCon42Viaggi = 0;
        for(Aeromobile aeromobile : listaAeromobili){
            if(aeromobile.getViaggiUltimoAnno() == 42){
                numeroAereiCon42Viaggi++;
            }
        }
        System.out.println(numeroAereiCon42Viaggi);

        //Stampare il numero di aeroporti non collegati
        int aeroportiNonCollegati = 0;
        for(Aeroporto aeroporto : listaAeroporti){
            boolean collegato = false;
            for(Viaggio viaggio : listaViaggi){
                if(viaggio.getAeroportoDiPartenza().equals(aeroporto.getCodice()) || viaggio.getAeroportoDiArrivo().equals(aeroporto.getCodice())){
                    collegato = true;
                    break;
                }
            }
            if(!collegato){
                aeroportiNonCollegati++;
            }
        }
        System.out.println(aeroportiNonCollegati);

        //Stampare il numero di giorni in cui hanno viaggiato complessivamente almeno 815 passeggeri
        int giorniConAlmeno815Passeggeri = 0, passeggeriTotali = 0;
        for(int i = 1; i < 366; i++){
            for(Viaggio viaggio : listaViaggi){
                if(i == viaggio.getGiornoDelVolo()){
                    passeggeriTotali += viaggio.getNumeroPasseggeri();
                }
                if(passeggeriTotali >= 815){
                    passeggeriTotali = 0;
                    giorniConAlmeno815Passeggeri++;
                }
            }
        }
        System.out.println(giorniConAlmeno815Passeggeri);
    }

    public static void task2(List<Aeromobile> listaAeromobili, List<Aeroporto> listaAeroporti, List<Viaggio> listaViaggi, int p, int q){
        //Istanziamento condizioni
        boolean condizione1 = false, condizione2 = false, condizione3 = false, condizione4 = false;

        //Controllo esistenza di almeno P aeromobili coinvolti in almeno Q viaggi
        int contatoreViaggi = 0, contatoreAeromobiliTotale = 0;

        for(Aeromobile aeromobile : listaAeromobili){
            int contatoreAeromobiliPerViaggio = 0;
            for(Viaggio viaggio : listaViaggi){
                if(viaggio.getAeromobile().equals(aeromobile.getId())){
                    contatoreAeromobiliPerViaggio++;
                    contatoreViaggi++;
                }
            }
            if(contatoreAeromobiliPerViaggio > 0){
                contatoreAeromobiliTotale += contatoreAeromobiliPerViaggio;
            }
        }
        if(contatoreAeromobiliTotale >= p && contatoreViaggi >= q){
            condizione1 = true;
            System.out.println("CONDIZIONE 1");
        }

        //Controllo esistenza di almeno Q viaggi con almeno un aeromobile in comune che si tengono a meno di due giorni di distanza
        int contatoreAeromobiliInComune = 0, giorno2 = 0;

        for(Aeromobile aeromobile : listaAeromobili){
            for(Viaggio viaggio : listaViaggi){
                if(aeromobile.getId().equals(viaggio.getAeromobile())){
                    int giorno1 = viaggio.getGiornoDelVolo();
                    if(giorno1 > giorno2){
                        int temp = giorno1;
                        giorno1 = giorno2;
                        giorno2 = temp;
                    }
                    if((giorno2 - giorno1) >= 0 && (giorno2 - giorno1) <= 2){
                        contatoreAeromobiliInComune++;
                    }
                }
            }
        }
        if(contatoreAeromobiliInComune >= p){
            condizione2 = true;
            System.out.println("CONDIZIONE 2");
        }

        //Controllo esistenza di almeno P e massimo Q aeroporti coinvolti in viaggi Overboard
        int contatoreAeroportiConViaggiOverboard = 0;
        for(Aeromobile aeromobile : listaAeromobili){
            for(Aeroporto aeroporto : listaAeroporti){
                for(Viaggio viaggio : listaViaggi){
                    if(aeromobile.getId().equals(viaggio.getAeromobile()) && (aeroporto.getCodice().equals(viaggio.getAeroportoDiPartenza()) || aeroporto.getCodice().equals(viaggio.getAeroportoDiArrivo())) && String.valueOf(viaggio.getTipoViaggio(aeromobile)).equals("OVERBOARD")){
                        contatoreAeroportiConViaggiOverboard++;
                    }
                }
            }
        }
        if(contatoreAeroportiConViaggiOverboard >= p && contatoreAeroportiConViaggiOverboard <= q){
            condizione3 = true;
            System.out.println("CONDIZIONE 3");
        }

        //Controllo esistenza di almeno Q viaggi i cuii aeroporti di partenza e destinazione non risiedano nella stessa nazione
        int contatoreViaggiStranieri = 0;
        for(Viaggio viaggio : listaViaggi){
            String nazionePartenza = "", nazioneArrivo = "";
            for(Aeroporto aeroportoPartenza : listaAeroporti){
                if(viaggio.getAeroportoDiPartenza().equals(aeroportoPartenza.getCodice())){
                    nazionePartenza = aeroportoPartenza.getNazione();
                }
            }
            for(Aeroporto aeroportoArrivo : listaAeroporti){
                if(viaggio.getAeroportoDiArrivo().equals(aeroportoArrivo.getCodice())){
                    nazioneArrivo = aeroportoArrivo.getNazione();
                }
            }
            if(nazioneArrivo.equals(nazionePartenza)){
                contatoreViaggiStranieri++;
            }
        }
        if(contatoreViaggiStranieri >= p){
            condizione4 = true;
            System.out.println("CONDIZIONE 4");
        }

        //Verifica delle condizioni
        if(condizione1 && condizione2 && condizione3 && condizione4){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void task3(List<Aeromobile> listaAeromobili, List<Aeroporto> listaAeroporti, List<Viaggio> listaVecchiaViaggi, List<Viaggio> listaNuoviViaggi){
        boolean condizione1 = false, condizione2 = false, condizione3 = false;

        //Condizione 1: almeno un aeroporto che non appare nella nuova lista viaggi
        int contatoreAeroportiNonPresenti = 0;
        for(Aeroporto aeroporto : listaAeroporti){
            for(Viaggio viaggioVecchio : listaVecchiaViaggi){
                if(!(aeroporto.getCodice().equals(viaggioVecchio.getAeroportoDiPartenza()) || aeroporto.getCodice().equals(viaggioVecchio.getAeroportoDiArrivo()))){
                    contatoreAeroportiNonPresenti++;
                }
            }

            for(Viaggio viaggioNuovo : listaNuoviViaggi){
                if(!(aeroporto.getCodice().equals(viaggioNuovo.getAeroportoDiPartenza()) || aeroporto.getCodice().equals(viaggioNuovo.getAeroportoDiArrivo()))){
                    contatoreAeroportiNonPresenti++;
                }
            }
        }
        if(contatoreAeroportiNonPresenti > 0){
            condizione1 = true;
        }

        //Condizione 2: Almeno un viaggio ponte
        boolean status1 = false, status2 = false;
        String idAereo1 = "", idAereo2 = "";
        for(Viaggio viaggioNuovo : listaNuoviViaggi){
            for(Viaggio viaggioVecchio : listaVecchiaViaggi){
                if(viaggioNuovo.getAeroportoDiPartenza().equals(viaggioVecchio.getAeroportoDiArrivo()) && (viaggioNuovo.getGiornoDelVolo() - viaggioVecchio.getGiornoDelVolo()) == 1 && viaggioNuovo.getAeromobile().equals(viaggioVecchio.getAeromobile())){
                    status1 = true;
                    idAereo1 = viaggioNuovo.getAeromobile();
                    break;
                } else {
                    status1 = false;
                }
            }

            for(Viaggio viaggioVecchio : listaVecchiaViaggi){
                if(viaggioNuovo.getAeroportoDiArrivo().equals(viaggioVecchio.getAeroportoDiPartenza()) && (viaggioVecchio.getGiornoDelVolo() - viaggioNuovo.getGiornoDelVolo()) == 1 && viaggioNuovo.getAeromobile().equals(viaggioVecchio.getAeromobile())){
                    status2 = true;
                    idAereo2 = viaggioNuovo.getAeromobile();
                    break;
                } else {
                    status2 = false;
                }
            }

            if(status1 && status2 && idAereo1.equals(idAereo2)){
                condizione2 = true;
            }
        }

        //Condizione 3: nella nuova lista ci deve essere almeno un viaggio ordinario
        int viaggiOrdinari = 0;
        for(Viaggio viaggio : listaNuoviViaggi){
            String tipoViaggio = "";
            for (Aeromobile aereo : listaAeromobili){
                if(viaggio.getAeromobile().equals(aereo.getId())){
                    tipoViaggio = String.valueOf(viaggio.getTipoViaggio(aereo));
                    }
                if(tipoViaggio.equals("ORDINARIO")){
                    viaggiOrdinari++;
                }
            }
        }
        if(viaggiOrdinari >= 1){
            condizione3 = true;
        }

        //Verifica delle condizioni
        if(condizione1 && condizione2 && condizione3){
            System.out.println("VALID");
        } else {
            System.out.println("NOT VALID");
        }
    }
    private static List<String> getStringList(List<Aeroporto> listaAeroporti) {
        List<String> listaAeroportiOverboard = new ArrayList<>();
        for(Aeroporto aeroporto : listaAeroporti){
            listaAeroportiOverboard.add(aeroporto.getCodice());
        }
        int numeroMaxAerei = 0;
        for(Aeroporto aeroporto : listaAeroporti){
            if(numeroMaxAerei < aeroporto.getNumeroMaxAeromobiliAlGiorno()){
                numeroMaxAerei = aeroporto.getNumeroMaxAeromobiliAlGiorno();
            }
            if(aeroporto.getNumeroMaxAeromobiliAlGiorno() < numeroMaxAerei){
                listaAeroportiOverboard.remove(aeroporto.getCodice());
            }
        }
        return listaAeroportiOverboard;
    }
}

