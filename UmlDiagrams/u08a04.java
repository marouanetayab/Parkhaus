class U08A04 {
    public int sumKurse(){
        return kurse.stream()
                .mapToInt(k -> k.gebühr())
                .reduce(0,(x,y)-> x+y);
    }


    public int sumStudenten(){
        return studenten.stream()
                .filter(s -> s.istBerufstätig())
                .mapToInt(s -> s.kurse.stream().mapToInt(k -> k.gebühr()).reduce(0,(x,y) -> x+y))
                .reduce(0,(x,y)->x+y);
    }


    public double avg(){
        return kurse.stream()
                .filter(k -> k.hatZertifikat())
                .filter(k -> k.ECTS > 4)
                .mapToInt(k -> k.gebühr())
                .reduce(0,(x,y)->(x+y)/2);
    }
}