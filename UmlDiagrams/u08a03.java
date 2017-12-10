class U08A03 {
    public static void main(String[] args) {
        List<Auto> autos = Arrays.asList(a1, a2, a3, a4, a5);
        autos.stream()
                .filter(a -> a.kunde().istFirma())
                .filter(a -> a.hatTurbo())
                .mapToInt(a -> a.parkdauer() * Parkhaus.gebühr())
                .reduce(0, (x, y) -> x + y);


        List<Auto> autos = Arrays.asList(a1, a2, a3, a4, a5);
        autos.stream()
                .filter(a -> a.kunde().istFirma())
                .filter(a -> a.parkdauer() >= (24 * 60))
                .map(a -> a.kunde());
    }
}