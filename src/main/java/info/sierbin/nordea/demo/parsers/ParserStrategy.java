package info.sierbin.nordea.demo.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParserStrategy {
    private Map<ParserType, Parser> strategies;

    @Autowired
    public ParserStrategy(Set<Parser> strategySet) {
        this.strategies = new HashMap<>();
        strategySet.forEach(
            strategy -> this.strategies.put(strategy.getType(), strategy));
    }

    public Parser find(ParserType type) {
        return strategies.get(type);
    }
}
