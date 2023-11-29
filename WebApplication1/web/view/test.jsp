<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            div.sticky {
                position: -webkit-sticky;
                position: sticky;
                top: 0;
                background-color: yellow;
                padding: 50px;
                font-size: 20px;
            }

            #u{
                border: 1px solid black;
                display: none;
                width: 20px;
            }

            #textarea{
                height: 200px;

                line-height: 25px;
            }
        </style>
    </head>
    <body>
        <p><span style="background-color: rgb(255, 255, 0);">About this subject, based on a specific topic, you need to build a corresponding software product. The progress will be divided into 3 parts: Iteration 1, Iteration 2 and Iteration 3. On each iteration, you will need to complete a specific amount of tasks to pass this subject. This video simply wants to introduce and explain to you more clearly about Iteration 1: what you guys must do in this process and how to do it. Moreover, this may give you many suggestions about what you guys want to do in Iteration 2 and Iteration 3. We hope that you will find this video in particular and the lesson in general interesting, challenging, so that you will have a good experience to prepare for OJT. And the last thing, we want to thank you all for choosing to study this subject and we wish for you all the best things in the world.</span></p><p><span style="background-color: rgb(255, 255, 0);">kdoaw</span><p></p><p></p></p>
        <textarea id = "textarea"></textarea>
        <button id="download-btn">Download</button>
        <script>
            const downloadBtn = document.querySelector("#download-btn");
            const fileUrl = "C:\SWP\file\2023110407111652.xlsx";

            downloadBtn.addEventListener("click", () => {
                window.open(fileUrl, "_blank", "download");
            });
        </script>
    </body>
</html>
