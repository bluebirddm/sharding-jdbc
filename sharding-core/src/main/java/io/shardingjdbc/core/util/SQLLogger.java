/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingjdbc.core.util;

import io.shardingjdbc.core.parsing.parser.sql.SQLStatement;
import io.shardingjdbc.core.routing.SQLExecutionUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

/**
 * SQL logger.
 * 
 * @author zhangliang 
 */
@Slf4j(topic = "Sharding-JDBC-SQL")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLLogger {
    
    /**
     * Print SQL log.
     * 
     * @param logicSQL logic SQL
     * @param sqlStatement SQL statement
     * @param sqlExecutionUnits SQL execution units
     * @param parameters parameters for SQL placeholder
     */
    public static void logSQL(final String logicSQL, final SQLStatement sqlStatement, final Collection<SQLExecutionUnit> sqlExecutionUnits, final List<Object> parameters) {
        log("Logic SQL: {}", logicSQL);
        log("SQLStatement: {}", sqlStatement);
        for (SQLExecutionUnit each : sqlExecutionUnits) {
            if (parameters.isEmpty()) {
                log("Actual SQL: {} ::: {}", each.getDataSource(), each.getSql());
            } else {
                log("Actual SQL: {} ::: {} ::: {}", each.getDataSource(), each.getSql(), parameters);
            }
        }
    }
    
    private static void log(final String pattern, final Object... arguments) {
        log.info(pattern, arguments);
    }
}
