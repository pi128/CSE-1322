public class DNALib {

    // Recursively validates that:
    // 1. The string is not empty.
    // 2. Its length is a multiple of 3.
    // 3. It contains only the characters A, C, G, and T.
    public static boolean validator(String dna) {
        if (dna == null || dna.length() == 0) {
            return false;
        }
        if (dna.length() % 3 != 0) {
            return false;
        }
        return validateChars(dna);
    }

    // Helper method to recursively check each character.
    private static boolean validateChars(String s) {
        if (s.length() == 0) {
            return true;
        }
        char c = s.charAt(0);
        if (c != 'A' && c != 'C' && c != 'G' && c != 'T') {
            return false;
        }
        return validateChars(s.substring(1));
    }

    // Recursively reverses the input string.
    public static String reverser(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return reverser(s.substring(1)) + s.charAt(0);
    }

    // Recursively replaces each nucleotide with its complement:
    // A <-> T and C <-> G.
    public static String inverser(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        char inverse = getComplement(first);
        return inverse + inverser(s.substring(1));
    }

    // Helper method for nucleotide complementation.
    private static char getComplement(char nucleotide) {
        switch (nucleotide) {
            case 'A': return 'T';
            case 'T': return 'A';
            case 'C': return 'G';
            case 'G': return 'C';
            default:  return nucleotide; // Should not occur as input is validated.
        }
    }

    // Recursively translates a DNA string (assumed valid and of length multiple of 3)
    // into a protein string by reading codons (3 characters) at a time.
    public static String translator(String s) {
        if (s.length() == 0) {
            return "";
        }
        String codon = s.substring(0, 3);
        String aminoAcid = translateCodon(codon);
        return aminoAcid + translator(s.substring(3));
    }

    // Uses a switch statement to map each codon to its corresponding amino acid.
    private static String translateCodon(String codon) {
        String aminoacid;
        switch(codon) {
            case "GCA":
            case "GCC":
            case "GCG":
            case "GCT":
                aminoacid = "A";
                break;
            case "TGC":
            case "TGT":
                aminoacid = "C";
                break;
            case "GAC":
            case "GAT":
                aminoacid = "D";
                break;
            case "GAA":
            case "GAG":
                aminoacid = "E";
                break;
            case "TTC":
            case "TTT":
                aminoacid = "F";
                break;
            case "GGA":
            case "GGC":
            case "GGG":
            case "GGT":
                aminoacid = "G";
                break;
            case "CAC":
            case "CAT":
                aminoacid = "H";
                break;
            case "ATA":
            case "ATC":
            case "ATT":
                aminoacid = "I";
                break;
            case "AAA":
            case "AAG":
                aminoacid = "K";
                break;
            case "CTA":
            case "CTC":
            case "CTG":
            case "CTT":
            case "TTA":
            case "TTG":
                aminoacid = "L";
                break;
            case "ATG":
                aminoacid = "M";
                break;
            case "AAT":
            case "AAC":
                aminoacid = "N";
                break;
            case "CCA":
            case "CCC":
            case "CCG":
            case "CCT":
                aminoacid = "P";
                break;
            case "CAA":
            case "CAG":
                aminoacid = "Q";
                break;
            case "AGA":
            case "AGG":
            case "CGA":
            case "CGC":
            case "CGG":
            case "CGT":
                aminoacid = "R";
                break;
            case "AGC":
            case "AGT":
            case "TCA":
            case "TCC":
            case "TCG":
            case "TCT":
                aminoacid = "S";
                break;
            case "ACA":
            case "ACC":
            case "ACG":
            case "ACT":
                aminoacid = "T";
                break;
            case "GTA":
            case "GTC":
            case "GTG":
            case "GTT":
                aminoacid = "V";
                break;
            case "TGG":
                aminoacid = "W";
                break;
            case "TAC":
            case "TAT":
                aminoacid = "Y";
                break;
            case "TAA":
            case "TAG":
            case "TGA":
                aminoacid = "-";
                break;
            default:
                aminoacid = "?"; // In case an unknown codon is encountered.
                break;
        }
        return aminoacid;
    }
}