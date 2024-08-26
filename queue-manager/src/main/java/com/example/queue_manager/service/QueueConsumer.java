package com.example.queue_manager.service;

import com.example.queue_manager.client.QueueClient;
import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.PlayerRepository;
import com.example.queue_manager.repository.QueueRepository;
import com.example.queue_manager.service.dto.PlayerDto;
import com.example.queue_manager.service.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class QueueConsumer {
    private final QueueClient client;
    private final PlayerRepository playerRepository;
    private final QueueRepository queueRepository;
    private final PlayerMapper mapper;

    private static final int MAX_RETRIES = 3;

    @Scheduled(fixedRate = 10000)
    public void runJobs() {
//         get all entries from queue with status TODO
        List<Queue> commands = getExecutionCommands();
//         run BusinessLogicService for each entries
        commands.forEach(queue -> updateExecutionCommand(executeJobLogicForCommands(queue)));
        System.out.println(":)");
    }

    public void userRequest() {
        runJob();
    }

    private void runJob() {
        List<PlayerDto> response = client.getQueueData();
        response.forEach(playerDto -> playerRepository.save(mapper.toEntity(playerDto)));
    }

//     update entry with proper status after job execution
    private void updateExecutionCommand(Queue queue) {
        queueRepository.save(queue);
    }

    private List<Queue> getExecutionCommands() {
        return queueRepository.findAll().stream()
                .filter(queue -> queue.getStatus().equals("TODO"))
                .toList();
    }

    private Queue executeJobLogicForCommands(Queue queue) {
        int retries = 0;
        boolean success = false;

        while (retries < MAX_RETRIES && !success) {
            try {
                Instant jobStart = Instant.now();
                runJob();
                Instant jobEnd = Instant.now();
                success = true;
                queue.setStatus("DONE");
                queue.setExecutionTime(Duration.between(jobStart, jobEnd));
                queue.setExecutionDate(jobEnd);
            } catch (Exception exception) {
                retries++;
                if (retries == MAX_RETRIES) {
                    queue.setStatus("FAILED");
                    queue.setExecutionDate(Instant.now());
                } else {
                    try {
                        long delayInMinutes = (retries == 1) ? 2 : 15;
                        TimeUnit.MINUTES.sleep(delayInMinutes);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        return queue;
    }
}

// retry (max 3 razy) gdy failed
// data-czas wykonania w queue
// czas wykonywania job'a w queue
// * retry - 1wszy tj. next job, 2gi po 2 min, 3ci po 15 min
// * wizualizacja czasów na grafanie
