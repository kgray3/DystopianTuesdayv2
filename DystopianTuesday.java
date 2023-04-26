import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

// Durations for bassline - w, h, q; Octaves - 3,4

public class DystopianTuesday {
    public static String[] keyArr;
    public static String[] melodyDurationArr;
    public static String[] melodyOctaveArr;
    public static String[] bassOctaveArr;
    public static String[] bassDurationArr;
    public static String[] majorMinorArr;
    public static HashMap<String, String[]> majorScales = new HashMap<String, String[]>();
    public static HashMap<String, String[]> minorScales = new HashMap<String, String[]>();
    public static HashMap<String, Double> durationToDoub = new HashMap<String, Double>();
    
    // assumption #1: we are in 4/4
    public static void main(String[] args) throws InterruptedException {
        initializeElements();
        
        int tempo = (int) (Math.random() * 80) + 70;
        System.out.println("The tempo is " + tempo + " bpm.");

        String majorMinor = pickRandomArrElement(majorMinorArr);
        String songKey = pickRandomArrElement(keyArr);
        String keyMM = "K" + songKey + majorMinor.substring(0,3);
        //generateRhythm(keyMM, majorMinor);
        System.out.println("The key is " + songKey + majorMinor + ".");
        for( int i = 0; i < 5; i++) {

            String introMelody = generateRandomNotes(songKey, majorMinor, 8, melodyOctaveArr, melodyDurationArr);
            String introBassline = generateRandomNotes(songKey, majorMinor, 8, bassOctaveArr, bassDurationArr);
            Pattern introChordProg = generateChordProgression(keyMM, majorMinor).setTempo(tempo);
            Pattern introRhythm = generateRhythm(keyMM, majorMinor);

            String verseMelody = generateRandomNotes(songKey, majorMinor, 8, melodyOctaveArr, melodyDurationArr);
            String verseBassline = generateRandomNotes(songKey, majorMinor, 8, bassOctaveArr, bassDurationArr);
            Pattern verseChordProg = generateChordProgression(keyMM, majorMinor).setTempo(tempo);
            Pattern verseRhythm = generateRhythm(keyMM, majorMinor);

            String chorusMelody = generateRandomNotes(songKey, majorMinor, 8, melodyOctaveArr, melodyDurationArr);
            String chorusBassline = generateRandomNotes(songKey, majorMinor, 8, bassOctaveArr, bassDurationArr);
            Pattern chorusChordProg = generateChordProgression(keyMM, majorMinor).setTempo(tempo);
            Pattern chorusRhythm = generateRhythm(keyMM, majorMinor);

            String bridgeMelody = generateRandomNotes(songKey, majorMinor, 8, melodyOctaveArr, melodyDurationArr);
            String bridgeBassline = generateRandomNotes(songKey, majorMinor, 8, bassOctaveArr, bassDurationArr);
            Pattern bridgeChordProg = generateChordProgression(keyMM, majorMinor).setTempo(tempo);
            Pattern bridgeRhythm = generateRhythm(keyMM, majorMinor);

            // String intro = keyMM + " V0 I[PIANO] " + introMelody + " V1 I[ELECTRIC_BASS_PICK] " + introBassline + " V2 I[PIANO] " + introChordProg + " V3 " + introRhythm.repeat(2);
            // String verse = keyMM + " V0 I[PIANO] " + verseMelody + " V1 I[ELECTRIC_BASS_PICK] " + verseBassline + " V2 I[PIANO] " + introChordProg + " V3 " + verseRhythm.repeat(2);
            // String chorus = keyMM + " V0 I[PIANO] " + chorusMelody + " V1 I[ELECTRIC_BASS_PICK] " + chorusBassline + " V2  I[PIANO] " + introChordProg + " V3 " + chorusRhythm.repeat(2);
            // String bridge = keyMM + " V0 I[PIANO] " + bridgeMelody + " V1 I[ELECTRIC_BASS_PICK] " + bridgeBassline + " V2 I[PIANO] " + introChordProg + " V3 " + bridgeRhythm.repeat(2);

            // String intro = keyMM + " V0 I[FLUTE] " + introMelody + " V1 I[ELECTRIC_BASS_PICK] " + introBassline +  " V3 " + introRhythm.repeat(2);
            // String verse = keyMM + " V0 I[FLUTE] " + verseMelody + " V1 I[ELECTRIC_BASS_PICK] " + verseBassline +  " V3 " + verseRhythm.repeat(2);
            // String chorus = keyMM + " V0 I[FLUTE] " + chorusMelody + " V1 I[ELECTRIC_BASS_PICK] " + chorusBassline +  " V3 " + chorusRhythm.repeat(2);
            // String bridge = keyMM + " V0 I[FLUTE] " + bridgeMelody + " V1 I[ELECTRIC_BASS_PICK] " + bridgeBassline + " V3 " + bridgeRhythm.repeat(2);
            



        Pattern introPattern = generatePartPattern(keyMM, new String[] {introMelody, introBassline, introChordProg.toString(), introRhythm.repeat(2).toString()}, tempo);
        Pattern versePattern = generatePartPattern(keyMM, new String[] {verseMelody, verseBassline, introChordProg.toString(), verseRhythm.repeat(2).toString()}, tempo);
        Pattern versePattern2 = generatePartPattern(keyMM, new String[] {verseMelody, verseBassline, introChordProg.toString(), verseRhythm.repeat(2).toString()}, tempo);
        Pattern chorusPattern = generatePartPattern(keyMM, new String[] {chorusMelody, chorusBassline, introChordProg.toString(), chorusRhythm.repeat(2).toString()}, tempo);
        Pattern chorusPattern2 = generatePartPattern(keyMM, new String[] {chorusMelody, chorusBassline, introChordProg.toString(), chorusRhythm.repeat(2).toString()}, tempo);
        Pattern bridgPattern = generatePartPattern(keyMM, new String[] {bridgeMelody, bridgeBassline, introChordProg.toString(), bridgeRhythm.repeat(2).toString()}, tempo);
            // Pattern randomSongPattern = new Pattern(randomSong).setTempo(tempo);
            // Pattern stepwiseSongPattern = new Pattern(stepwiseSong).setTempo(tempo);
            // Pattern randomSongPattern2 = new Pattern(randomSong2).setTempo(tempo);
            // Pattern stepwiseSongPattern2 = new Pattern(stepwiseSong2).setTempo(tempo);
            
        
        // Player player = new Player();

        Pattern composition = new Pattern(introPattern, versePattern, versePattern2, chorusPattern, versePattern, versePattern2, chorusPattern2.repeat(2), bridgPattern, chorusPattern.repeat(2));



        try {
            File filePath = new File("D:/Programming Projects/Thesis Programs/Aleatory/midi/m" + i +  ".midi");
            MidiFileManager.savePatternToMidi(composition, filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }}



    }


    public static Pattern generatePartPattern(String key, String[] parts, int tempo) {
        String pat = key;
        String[] currentInst = {"[PIANO]","[ELECTRIC_BASS_PICK]", "[PIANO]", ""};
        for(int i = 0; i < 4; i++) {
            if(!((Math.random()*100) + 1 <= 10)) {
                pat += " V" + i + " I" + currentInst[i] + " " + parts[i]; 
            }
        }

        return new Pattern(pat).setTempo(tempo);
    }

    public static Pattern generateRhythm(String key, String majorMinor) {
        //Generate bass layer - snare layer - high hat layer - misc layer
        String bassLayer = "";
        String snareLayer = "";
        String highHatLayer = "";
        //String miscLayer = ".......+";

        String[] bassArr = {"O", ".", "."};
        String[] snareArr = {"S", ".", ".", "."};
        String[] highHatArr = {"`", "."};

        for(int i = 0; i < 8; i++) {
            bassLayer = bassLayer + pickRandomArrElement(bassArr);
            snareLayer = snareLayer + pickRandomArrElement(snareArr);
            highHatLayer = highHatLayer + pickRandomArrElement(highHatArr);

        }

        Rhythm rhythm = new Rhythm()
        .addLayer(bassLayer)
        .addLayer(snareLayer)
        .addLayer(highHatLayer);
        //.addLayer(miscLayer);

        Pattern p = new Pattern(rhythm);
        return p;
    
}

    public static Pattern generateChordProgression(String key, String majorMinor){
            String[] chordArr = {"I V VI IV", "VI IV I V ", "I IV V IV ", "II V I VI "};

            String chordProgression = pickRandomArrElement(chordArr);
            

            
              ChordProgression cp = new ChordProgression(chordProgression).setKey(key.substring(1));
              System.out.println(cp);
            //   player.play(cp.setKey(key).eachChordAs("$!h"));
              Pattern pattern = new Pattern(cp.setKey(key.substring(1)).eachChordAs("$!h"));
              return pattern;
    }

    public static String generateRandomNotes(String key, String majorMinor, int numNotes, String[] octaveArr, String[] durationArr) {
        ArrayList<String> noteDurationArr = generateDurationArray(durationArr, numNotes);
        String octave = pickRandomArrElement(octaveArr);

        String versePattern = "";
        
        if(majorMinor.equalsIgnoreCase("major")) {
            for(int i = 0; i < noteDurationArr.size(); i ++) {
                String note = pickRandomArrElement(majorScales.get(key)) + octave + noteDurationArr.get(i);
                versePattern = versePattern + note + " ";
            }
        } else {
            for(int i = 0; i < noteDurationArr.size(); i ++) {
                String note = pickRandomArrElement(minorScales.get(key)) + octave + noteDurationArr.get(i);
                versePattern = versePattern + note + " ";
            }
        }

        System.out.println("The random note string is: " + versePattern);
        
        return versePattern;

    }

    public static int arrayIndexOf(String[] arr, String element) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public static String generateStepwiseNotes(String key, String majorMinor, int numNotes, String[] octaveArr, String[] durationArr) {
        String versePattern = "";
        ArrayList<String> noteDurationArr = generateDurationArray(durationArr, numNotes);
        ArrayList<Integer> notePosition = new ArrayList<>();
        String octave = pickRandomArrElement(octaveArr);
        if(majorMinor.equalsIgnoreCase("major")) {
            for(int i = 0; i < noteDurationArr.size(); i ++) {
                String pitch = pickStepwiseArrayElement(majorScales.get(key), notePosition, numNotes);
                notePosition.add(arrayIndexOf(majorScales.get(key),pitch));
                String note = pitch + octave + noteDurationArr.get(i);
                versePattern = versePattern + note + " ";
            }
        } else {
            for(int i = 0; i < noteDurationArr.size(); i ++) {
                String pitch = pickStepwiseArrayElement(minorScales.get(key), notePosition, numNotes);
                notePosition.add(arrayIndexOf(minorScales.get(key),pitch));
                String note = pitch + octave + noteDurationArr.get(i);
                versePattern = versePattern + note + " ";
            }
        }

        System.out.println("The stepwise note string is: " + versePattern);
    
        return versePattern;
    }

    public static String pickStepwiseArrayElement(String[] scaleArr, ArrayList<Integer> notePositions, int numNotes) {
        // notes can be repeated, one step, or two step above/below current note
        
        if(notePositions.size() == 0) {
            return pickRandomArrElement(scaleArr);
        } else {
            int[] choiceArr = new int[]{-2,-1,-1,-1,0,1,1,1,2};
            
            while(true) {
                int choice = choiceArr[(int)((Math.random() * choiceArr.length))];
                int previousNote = notePositions.get(notePositions.size()-1);
                int newNotePosition = previousNote + choice;
                if(newNotePosition >= 0 && newNotePosition < scaleArr.length ){
                    return scaleArr[newNotePosition];
                }
            }
        }
    }

    public static ArrayList<String> generateDurationArray(String[] possibleDurations, int numNotes) {
        ArrayList<String> noteDurationArr = new ArrayList<>();
        
        while(arrayListSum(noteDurationArr) != numNotes) {
            if(noteDurationArr.size() > numNotes) {
                noteDurationArr = new ArrayList<>();
            }
            noteDurationArr.add(pickRandomArrElement(possibleDurations));
        }

        return noteDurationArr;
    }


    public static double arrayListSum(ArrayList<String> arr) {
        double sum = 0;

        if(arr != null){
        for(String i : arr) {
            sum += durationToDoub.get(i);
        }}
        return sum;
    }

    public static String pickRandomArrElement(String[] arr) {
        return arr[(int) (Math.random() * arr.length)]; 
    }

    public static void initializeElements() {

        keyArr = new String[] {"A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "F#", "G", "Ab"};
        majorMinorArr = new String[] {"major"};
        melodyOctaveArr = new String[] {"5","6"};
        bassOctaveArr = new String[] {"3"};
        
        melodyDurationArr = new String[]{"h", "q", "i"};
        bassDurationArr = new String[]{"w","h","q"};

        durationToDoub.put("w", 4.0);
        durationToDoub.put("h", 2.0);
        durationToDoub.put("q", 1.0);
        durationToDoub.put("i", 0.5);
        //durationToDoub.put("s", 0.25);
        //durationToDoub.put("t", 0.25/2.0);
        

        majorScales.put("A", new String[]{"A", "B", "C#", "D", "E", "F#", "G#"});
        majorScales.put("Bb", new String[]{"Bb", "C", "D", "Eb", "F", "G", "A"});
        majorScales.put("B", new String[]{"B", "C#", "D#", "E", "F#", "G#", "A#"});
        majorScales.put("C", new String[]{"C", "D", "E", "F", "G", "A", "B"});
        majorScales.put("Db", new String[]{"Db", "Eb", "F", "Gb", "Ab", "Bb", "C"});
        majorScales.put("D", new String[]{"D", "E", "F#", "G", "A", "B", "C#"});
        majorScales.put("Eb", new String[]{"Eb", "F", "G", "Ab", "Bb", "C", "D"});
        majorScales.put("E", new String[]{"E", "F#", "G#", "A", "B", "C#", "D#"});
        majorScales.put("F", new String[]{"F", "G", "A", "Bb", "C", "D", "E"});
        majorScales.put("F#", new String[]{"F#", "G#", "A#", "B", "C#", "D#", "E#"});
        majorScales.put("G", new String[]{"G", "A", "B", "C", "D", "E", "F#"});
        majorScales.put("Ab", new String[]{"Ab", "Bb", "C", "Db", "Eb", "F", "G"});

        minorScales.put("A", new String[]{"A", "B", "C", "D", "E", "F", "G"});
        minorScales.put("Bb", new String[]{"Bb", "C", "Db", "Eb", "F", "Gb", "Ab"});
        minorScales.put("B", new String[]{"B", "C#", "D", "E", "F#", "G", "A"});
        minorScales.put("C", new String[]{"C", "D", "Eb", "F", "G", "Ab", "Bb", "C"});
        minorScales.put("Db", new String[]{"C#", "D#", "E", "F#", "G#", "A", "B"});
        minorScales.put("D", new String[]{"D", "E", "F", "G", "A", "Bb", "C"});
        minorScales.put("Eb", new String[]{"Eb", "F", "Gb", "Ab", "Bb", "Cb", "Db"});
        minorScales.put("E", new String[]{"E", "F#", "G", "A", "B", "C", "D"});
        minorScales.put("F", new String[]{"F", "G", "Ab", "Bb", "C", "Db", "Eb"});
        minorScales.put("F#", new String[]{"F#", "G#", "A", "B", "C#", "D", "E"});
        minorScales.put("G", new String[]{"G", "A", "Bb", "C", "D", "Eb", "F"});
        minorScales.put("Ab", new String[]{"Ab", "Bb", "Cb", "Db", "Eb", "Fb", "Gb"});

    }
}