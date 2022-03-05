import javax.sound.midi.*;

public class MiniMusicCmdLine {

    public static void main(String [] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        if (args.length < 2) {
            System.out.println("Не забудьте аргументы для инструмента и ноты");
        } else {
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play();
        }
    }

    public void play(int instrument, int note) {
        try {
            // Получаем синтезатор и открываем его, чтобы начать использовать(по умолчанию он не откроется)
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);

            //Запрашиваем трек у последовательности
            Track track = seq.createTrack();

            MidiEvent event = null;

            //Помещаем в трек Midi-события
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

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
