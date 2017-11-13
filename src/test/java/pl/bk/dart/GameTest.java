package pl.bk.dart;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Game.class)
public class GameTest {

    @Test
    public void shouldAddPlayerToGame(){
        //given
        PointAsker pointAsker = new PointAsker();
        Game game = new Game(pointAsker);
        Player player = new Player("Adam");
        //when
        game.addPlayer(player);
        //then
        Assert.assertTrue(game.takeAllPlayers().size() == 1);
    }

    @Test
    public void shouldCreatePlayerWith501Points(){
        //given
        PointAsker pointAsker = new PointAsker();
        Game game = new Game(pointAsker);
        Player player = new Player("Adam");
        //when
        game.addPlayer(player);
        //then
        Assert.assertTrue(player.getNumberOfPoints() == 501);
    }

    @Test
    public void shouldSubtractPointsFromPlayer(){
        //given
        PointAsker pointAsker = new PointAsker();
        Game game = new Game(pointAsker);
        Player player = new Player("Adam");
        //when
        game.addPlayer(player);
        player.subtractPoints(100);
        //then
        Assert.assertTrue(player.getNumberOfPoints() == 401);
    }

    @Test
    public void shouldStartGame() throws Exception {
        //given
        Player player = new Player("Adam");
        PointAsker mockedPointAsker = Mockito.mock(PointAsker.class);
        Game game = new Game(mockedPointAsker);
        //when
        int returnValue = 10;
        when(mockedPointAsker.ask()).thenReturn(returnValue);
        game.addPlayer(player);
        game.startGame();
    }
}
