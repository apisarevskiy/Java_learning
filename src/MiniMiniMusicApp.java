import javax.sound.midi.*;

public class MiniMiniMusicApp {

    public static void main(String[] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
                mini.play();
    }

    public void play() {

        try {
            // Получаем синтезатор и открываем его, чтобы начать использовать(по умолчанию он не откроется)
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);

            //Запрашиваем трек у последовательности
            Track track = seq.createTrack();

            //Помещаем в трек Midi-события
            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 44, 100);
            MidiEvent noteOne = new MidiEvent(a, 1);
            track.add(noteOne);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            //Передаём последоваетельность синтезатору
            player.setSequence(seq);

            //Запускаем синтезатор
            player.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
