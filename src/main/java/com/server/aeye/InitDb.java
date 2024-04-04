package com.server.aeye;

import com.server.aeye.domain.Team;
import com.server.aeye.domain.Video;
import com.server.aeye.domain.VideoSummary;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

//    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        String string = "You should never take advice from someone who thinks red paint dries quicker than blue paint.\n"
            + "They called out her name time and again, but were met with nothing but silence.\n"
            + "Situps are a terrible way to end your day.\n"
            + "Various sea birds are elegant, but nothing is as elegant as a gliding pelican.\n"
            + "His thought process was on so many levels that he gave himself a phobia of heights.\n"
            + "The spa attendant applied the deep cleaning mask to the gentleman’s back.\n"
            + "Tomatoes make great weapons when water balloons aren’t available.\n"
            + "The dead trees waited to be ignited by the smallest spark and seek their revenge.\n"
            + "It's a skateboarding penguin with a sunhat!\n"
            + "The murder hornet was disappointed by the preconceived ideas people had of him.\n"
            + "The shark-infested South Pine channel was the only way in or out.\n"
            + "He liked to play with words in the bathtub.\n"
            + "Smoky the Bear secretly started the fires.\n"
            + "The irony of the situation wasn't lost on anyone in the room.\n"
            + "The bread dough reminded her of Santa Clause’s belly.\n"
            + "Combines are no longer just for farms.\n"
            + "The elephant didn't want to talk about the person in the room.\n"
            + "She had convinced her kids that any mushroom found on the ground would kill them if they touched it.\n"
            + "Your girlfriend bought your favorite cookie crisp cereal but forgot to get milk.\n"
            + "Car safety systems have come a long way, but he was out to prove they could be outsmarted.\n"
            + "Nothing is as cautiously cuddly as a pet porcupine.\n"
            + "He dreamed of leaving his law firm to open a portable dog wash.\n"
            + "Joyce enjoyed eating pancakes with ketchup.\n"
            + "The trick to getting kids to eat anything is to put catchup on it.\n"
            + "A suit of armor provides excellent sun protection on hot days.\n"
            + "My biggest joy is roasting almonds while stalking prey.\n"
            + "Buried deep in the snow, he hoped his batteries were fresh in his avalanche beacon.\n"
            + "Joe discovered that traffic cones make excellent megaphones.\n"
            + "She wanted a pet platypus but ended up getting a duck and a ferret instead.\n"
            + "Swim at your own risk was taken as a challenge for the group of Kansas City college students.\n"
            + "Today I dressed my unicorn in preparation for the race.\n"
            + "Imagine his surprise when he discovered that the safe was full of pudding.\n"
            + "I became paranoid that the school of jellyfish was spying on me.\n"
            + "You bite up because of your lower jaw.\n"
            + "Peanuts don't grow on trees, but cashews do.\n"
            + "Douglas figured the best way to succeed was to do the opposite of what he'd been doing all his life.\n"
            + "He decided to live his life by the big beats manifesto.\n"
            + "He wasn't bitter that she had moved on but from the radish.\n"
            + "Today is the day I'll finally know what brick tastes like.\n"
            + "She let the balloon float up into the air with her hopes and dreams.\n"
            + "She did her best to help him.\n"
            + "The fish listened intently to what the frogs had to say.\n"
            + "They did nothing as the raccoon attacked the lady’s bag of food.\n"
            + "Jim liked driving around town with his hazard lights on.\n"
            + "I want to buy a onesie… but know it won’t suit me.\n"
            + "He waited for the stop sign to turn to a go sign.\n"
            + "The secret code they created made no sense, even to them.\n"
            + "The reservoir water level continued to lower while we enjoyed our long shower.\n"
            + "He watched the dancing piglets with panda bear tummies in the swimming pool.\n"
            + "The skeleton had skeletons of his own in the closet.\n"
            + "We have never been to Asia, nor have we visited Africa.\n"
            + "When he asked her favorite number, she answered without hesitation that it was diamonds.\n"
            + "He wondered if she would appreciate his toenail collection.\n"
            + "Be careful with that butter knife.\n"
            + "He was sitting in a trash can with high street class.\n"
            + "Cursive writing is the best way to build a race track.\n"
            + "Dan ate the clouds like cotton candy.\n"
            + "Being unacquainted with the chief raccoon was harming his prospects for promotion.\n"
            + "The family’s excitement over going to Disneyland was crazier than she anticipated.\n"
            + "The three-year-old girl ran down the beach as the kite flew behind her.\n"
            + "The secret code they created made no sense, even to them.\n"
            + "The tart lemonade quenched her thirst, but not her longing.\n"
            + "My dentist tells me that chewing bricks is very bad for your teeth.\n"
            + "The teens wondered what was kept in the red shed on the far edge of the school grounds.\n"
            + "She looked at the masterpiece hanging in the museum but all she could think is that her five-year-old could do better.\n"
            + "Sarah ran from the serial killer holding a jug of milk.\n"
            + "Getting up at dawn is for the birds.\n"
            + "The llama couldn't resist trying the lemonade.\n"
            + "He decided water-skiing on a frozen lake wasn’t a good idea.\n"
            + "He found the end of the rainbow and was surprised at what he found there.\n"
            + "Even though he thought the world was flat he didn’t see the irony of wanting to travel around the world.\n"
            + "It was her first experience training a rainbow unicorn.\n"
            + "Pair your designer cowboy hat with scuba gear for a memorable occasion.\n"
            + "The spa attendant applied the deep cleaning mask to the gentleman’s back.\n"
            + "He felt that dining on the bridge brought romance to his relationship with his cat.\n"
            + "He kept telling himself that one day it would all somehow make sense.\n"
            + "The blinking lights of the antenna tower came into focus just as I heard a loud snap.\n"
            + "Doris enjoyed tapping her nails on the table to annoy everyone.\n"
            + "Red is greener than purple, for sure.\n"
            + "Two more days and all his problems would be solved.\n"
            + "It isn't true that my mattress is made of cotton candy.\n"
            + "I want a giraffe, but I'm a turtle eating waffles.\n"
            + "I love bacon, beer, birds, and baboons.\n"
            + "Blue sounded too cold at the time and yet it seemed to work for gin.\n"
            + "Flesh-colored yoga pants were far worse than even he feared.\n"
            + "The door swung open to reveal pink giraffes and red elephants.\n"
            + "The sunblock was handed to the girl before practice, but the burned skin was proof she did not apply it.\n"
            + "If you don't like toenails, you probably shouldn't look at your feet.\n"
            + "At last\n"
            + "The complicated school homework left the parents trying to help their kids quite confused.\n"
            + "She only paints with bold colors; she does not like pastels.\n"
            + "Don't step on the broken glass.\n"
            + "Grape jelly was leaking out the hole in the roof.\n"
            + "The bug was having an excellent day until he hit the windshield.\n"
            + "Gwen had her best sleep ever on her new bed of nails.\n"
            + "She had that tint of craziness in her soul that made her believe she could actually make a difference.\n"
            + "She had some amazing news to share but nobody to share it with.\n"
            + "She insisted that cleaning out your closet was the key to good driving.\n"
            + "Thigh-high in the water, the fisherman’s hope for dinner soon turned to despair.\n"
            + "Instead of a bachelorette party, we had a playdate with the kids at the trampoline park.\n"
            + "The tears of a clown make my lipstick run, but my shower cap is still intact.\n"
            + "The anaconda was the greatest criminal mastermind in this part of the neighborhood.\n"
            + "She was too short to see over the fence.\n"
            + "I've traveled all around Africa and still haven't found the gnu who stole my scarf.\n"
            + "He put heat on the wound to see what would grow.\n"
            + "She can live her life however she wants as long as she listens to what I have to say.\n"
            + "The tortoise jumped into the lake with dreams of becoming a sea turtle.\n"
            + "He never understood why what, when, and where left out who.\n"
            + "The sky is clear; the stars are twinkling.\n"
            + "It's never comforting to know that your fate depends on something as unpredictable as the popping of corn.\n"
            + "The toy brought back fond memories of being lost in the rain forest.\n"
            + "As he entered the church he could hear the soft voice of someone whispering into a cell phone.\n"
            + "There are no heroes in a punk rock band.\n"
            + "Every manager should be able to recite at least ten nursery rhymes backward.\n"
            + "Honestly, I didn't care much for the first season, so I didn't bother with the second.\n"
            + "If you spin around three times, you'll start to feel melancholy.\n"
            + "She moved forward only because she trusted that the ending she now was going through must be followed by a new beginning.\n"
            + "He was all business when he wore his clown suit.\n"
            + "As he looked out the window, he saw a clown walk by.\n"
            + "I checked to make sure that he was still alive.\n"
            + "If I don’t like something, I’ll stay away from it.\n"
            + "They were excited to see their first sloth.\n"
            + "She let the balloon float up into the air with her hopes and dreams.\n"
            + "I really want to go to work, but I am too sick to drive.\n"
            + "The sight of his goatee made me want to run and hide under my sister-in-law's bed.\n"
            + "She was sad to hear that fireflies are facing extinction due to artificial light, habitat loss, and pesticides.\n"
            + "He stepped gingerly onto the bridge knowing that enchantment awaited on the other side.\n"
            + "Her life in the confines of the house became her new normal.\n"
            + "They finished building the road they knew no one would ever use.\n"
            + "Peter found road kill an excellent way to save money on dinner.\n"
            + "He was an introvert that extroverts seemed to love.\n"
            + "He shaved the peach to prove a point.\n"
            + "Sometimes, all you need to do is completely make an ass of yourself and laugh it off to realise that life isn’t so bad after all.\n"
            + "Writing a list of random sentences is harder than I initially thought it would be.\n"
            + "Random words in front of other random words create a random sentence.\n"
            + "The door slammed on the watermelon.\n"
            + "So long and thanks for the fish.\n"
            + "Dan ate the clouds like cotton candy.\n"
            + "The Great Dane looked more like a horse than a dog.\n"
            + "They decided to plant an orchard of cotton candy.\n"
            + "Nobody questions who built the pyramids in Mexico.\n"
            + "The rain pelted the windshield as the darkness engulfed us.\n"
            + "He was the only member of the club who didn't like plum pudding.\n"
            + "Everyone says they love nature until they realize how dangerous she can be.\n"
            + "She used her own hair in the soup to give it more flavor.\n"
            + "Tom got a small piece of pie.\n"
            + "Despite what your teacher may have told you, there is a wrong way to wield a lasso.\n"
            + "No matter how beautiful the sunset, it saddened her knowing she was one day older.\n"
            + "Today I bought a raincoat and wore it on a sunny day.\n"
            + "The bees decided to have a mutiny against their queen.\n"
            + "When he asked her favorite number, she answered without hesitation that it was diamonds.\n"
            + "The underground bunker was filled with chips and candy.\n"
            + "Car safety systems have come a long way, but he was out to prove they could be outsmarted.\n"
            + "The small white buoys marked the location of hundreds of crab pots.\n"
            + "The ice-cream trucks bring back bad memories for all of us.\n"
            + "The miniature pet elephant became the envy of the neighborhood.\n"
            + "She was only made the society president because she can whistle with her toes.\n"
            + "Siri became confused when we reused to follow her directions.\n"
            + "Flying fish flew by the space station.\n"
            + "Don't piss in my garden and tell me you're trying to help my plants grow.\n"
            + "Pink horses galloped across the sea.\n"
            + "After exploring the abandoned building, he started to believe in ghosts.\n"
            + "I love bacon, beer, birds, and baboons.\n"
            + "The gloves protect my feet from excess work.\n"
            + "At that moment he wasn't listening to music, he was living an experience.\n"
            + "Jeanne wished she has chosen the red button.\n"
            + "If you like tuna and tomato sauce, try combining the two, it’s really not as bad as it sounds.\n"
            + "The mysterious diary records the voice.\n"
            + "After fighting off the alligator, Brian still had to face the anaconda.\n"
            + "The sunblock was handed to the girl before practice, but the burned skin was proof she did not apply it.\n"
            + "We need to rent a room for our party.\n"
            + "I am happy to take your donation; any amount will be greatly appreciated.\n"
            + "Honestly, I didn't care much for the first season, so I didn't bother with the second.\n"
            + "Sometimes I stare at a door or a wall and I wonder what is this reality, why am I alive, and what is this all about?\n"
            + "Baby wipes are made of chocolate stardust.\n"
            + "He spiked his hair green to support his iguana.\n"
            + "The lyrics of the song sounded like fingernails on a chalkboard.\n"
            + "He dreamed of eating green apples with worms.\n"
            + "There are no heroes in a punk rock band.\n"
            + "Nancy was proud that she ran a tight shipwreck.\n"
            + "As time wore on, simple dog commands turned into full paragraphs explaining why the dog couldn’t do something.\n"
            + "25 years later, she still regretted that specific moment.\n"
            + "The fish dreamed of escaping the fishbowl and into the toilet where he saw his friend go.\n"
            + "As he dangled from the rope deep inside the crevasse\n"
            + "Dan took the deep dive down the rabbit hole.\n"
            + "Everyone was busy, so I went to the movie alone.\n"
            + "She had a habit of taking showers in lemonade.\n"
            + "He poured rocks in the dungeon of his mind.\n"
            + "He didn't understand why the bird wanted to ride the bicycle.\n"
            + "Random words in front of other random words create a random sentence.\n"
            + "The wake behind the boat told of the past while the open sea for told life in the unknown future.\n"
            + "For some unfathomable reason, the response team didn't consider a lack of milk for my cereal as a proper emergency.\n"
            + "Written warnings in instruction manuals are worthless since rabbits can't read.\n"
            + "He had a wall full of masks so she could wear a different face every day.\n"
            + "He embraced his new life as an eggplant.\n"
            + "Never underestimate the willingness of the greedy to throw you under the bus.\n"
            + "Normal activities took extraordinary amounts of concentration at the high altitude.\n"
            + "The river stole the gods.\n"
            + "Nobody questions who built the pyramids in Mexico.\n"
            + "Courage and stupidity were all he had.";

        List<String> strings = List.of(string.split("\n"));

        public void dbInit() {

            Team team = Team.builder()
                .name("team1")
                .description("team1 description")
                .build();
            em.persist(team);

            Video video = Video.builder()
                .title("video1")
                .description("video1 description")
                .team(team)
                .videoUri("https://storage.googleapis.com/aeye-bucket/vlog.mp4")
                .thumbnailUri("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSL2K1vuQb1Gds7URSA1AUC9vECOmHu3XKKQp4nYfjT0Q&s")
                .build();
            em.persist(video);

            strings.forEach(s -> {
                em.persist(VideoSummary.builder()
                    .content(s)
                    .time(LocalTime.parse("00:00:00"))
                    .video(video)
                    .build());
            });
        }
    }
}
