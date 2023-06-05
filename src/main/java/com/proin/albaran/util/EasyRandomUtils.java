package com.proin.albaran.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.FirstNameRandomizer;
import org.jeasy.random.randomizers.FullNameRandomizer;
import org.jeasy.random.randomizers.IsbnRandomizer;
import org.jeasy.random.randomizers.RegularExpressionRandomizer;
import org.jeasy.random.randomizers.WordRandomizer;


/**
 * The Class EasyRandomUtils.
 */
public class EasyRandomUtils {

    /**
     * Generate easy random.
     *
     * @return the easy random
     */
    public static EasyRandom generateEasyRandom() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L)
				.stringLengthRange(3, 10)
                .objectPoolSize(10)
                .randomizationDepth(10)
                .charset(UTF_8)
                .overrideDefaultInitialization(true)
                .stringLengthRange(10, 20)
                .collectionSizeRange(1, 10)
                .scanClasspathForConcreteTypes(true)
                .ignoreRandomizationErrors(true);
        return new EasyRandom(parameters);
    }

	public static RegularExpressionRandomizer cifGenerator() {
		return new RegularExpressionRandomizer("([a-z]|[A-Z]|[0-9])[0-9]{7}([a-z]|[A-Z]|[0-9])");
	}

	public static RegularExpressionRandomizer matriculaGenerator() {
		return new RegularExpressionRandomizer("^[0-9]{1,4}(?!.*(LL|CH))[BCDFGHJKLMNPRSTVWXYZ]{3}");
	}

    public static FirstNameRandomizer nombreGenerator() {
		return new FirstNameRandomizer();
	}

    public static FullNameRandomizer nombreCompletoGenerator() {
		return new FullNameRandomizer();
	}

    public static FirstNameRandomizer ciudadGenerator() {
		return new FirstNameRandomizer();
	}

    public static FirstNameRandomizer companiaGenerator() {
		return new FirstNameRandomizer();
	}

    public static WordRandomizer stringGenerator(long longitud) {
		return new WordRandomizer(longitud);
	}

    public static IsbnRandomizer numeroIsbnGenerator() {
		return new IsbnRandomizer();
	}

}