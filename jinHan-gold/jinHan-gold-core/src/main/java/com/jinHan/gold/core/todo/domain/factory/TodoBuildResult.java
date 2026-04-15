package com.jinHan.gold.core.todo.domain.factory;

import com.jinHan.gold.core.todo.domain.command.TodoCompleteCommand;
import com.jinHan.gold.core.todo.domain.command.TodoCreateCommand;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 待办构建结果
 */
@Data
public class TodoBuildResult {
    private final List<TodoCreateCommand> createCommands = new ArrayList<>();
    private final List<TodoCompleteCommand> completeCommands = new ArrayList<>();
}
