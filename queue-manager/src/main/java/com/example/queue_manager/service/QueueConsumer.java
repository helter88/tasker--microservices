package com.example.queue_manager.service;

import com.example.queue_manager.client.QueueClient;
import com.example.queue_manager.exception.InvalidJobException;
import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.PlayerRepository;
import com.example.queue_manager.repository.QueueRepository;
import com.example.queue_manager.service.dto.PlayerDto;
import com.example.queue_manager.service.mapper.PlayerMapper;
import com.example.queue_manager.service.utile.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueConsumer {
    private final QueueClient client;
    private final QueueRepository queueRepository;
    @Value("${queue.max-retries}")
    private int maxRetries;


    @Scheduled(fixedDelayString = "${queue.repetition-time}")
    public void runJobs() {
//         get all entries from queue with status TODO
        List<Queue> commands = getExecutionCommands();
//         run BusinessLogicService for each entries
        commands.forEach(queue -> updateExecutionCommand(executeJobLogicForCommands(queue)));
    }

    public void userRequest() {
        runJob();
    }

    private void runJob() {
    }

    private void updateExecutionCommand(Queue queue) {
        queueRepository.save(queue);
    }
    private List<Queue> getExecutionCommands() {
    }

    private Queue executeJobLogicForCommands(Queue queue) {

        try {
            Instant jobStart = Instant.now();
            queue.setStatus(Status.INPROGRESS);
            queueRepository.save(queue);
            runJob();
            Instant jobEnd = Instant.now();
            queue.setStatus(Status.DONE);
            queue.setExecutionTime(Duration.between(jobStart, jobEnd));
            queue.setExecutionDate(jobEnd);
        } catch (Exception exception) {
            //TODO write Exception Handler
            queue.setStatus(Status.FAILED);
            queue.setExecutionDate(Instant.now());
            queue.setNumberOfTries(queue.getNumberOfTries() + 1);
            var messageException = "Can't run Job";
            queue.setMessageLog(messageException);
            queueRepository.save(queue);
            throw new InvalidJobException(messageException);
        }
        return queue;
    }
}

